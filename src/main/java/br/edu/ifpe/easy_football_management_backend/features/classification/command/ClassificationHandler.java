package br.edu.ifpe.easy_football_management_backend.features.classification.command;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.BusinessException;
import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.NotFoundException;
import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import org.apache.coyote.BadRequestException;
import org.redisson.api.RedissonClient;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ClassificationHandler {

    private final ChampionshipsRepository championshipsRepository;
    private final MatchRepository matchRepository;
    private final RedissonClient redissonClient;
    private final ChampionshipsTeamsRepository championshipsTeamsRepository;

    public ClassificationHandler(
            ChampionshipsRepository championshipsRepository,
            MatchRepository matchRepository, RedissonClient redissonClient, ChampionshipsTeamsRepository championshipsTeamsRepository) {
        this.championshipsRepository = championshipsRepository;
        this.matchRepository = matchRepository;
        this.redissonClient = redissonClient;
        this.championshipsTeamsRepository = championshipsTeamsRepository;
    }

    @EventListener
    public void GenerateClassificationEvent(ChampionshipsEvent event) {
//        String keyLock = "ClassificationHandler.GenerateClassificationEvent." + event.getEventId();
//        var lock = redissonClient.getLock(keyLock);
        try {
            var championshipId = event.getChampionshipsId();
            Championships championship = championshipsRepository.findById(championshipId)
                    .orElseThrow(() -> new BusinessException("Campeonato não encontrado."));

            if (!championship.getStatus().equals(StatusChampionship.CREATE)) {
                throw new BusinessException("Não é possível gerar partidas para um campeonato que não está no status 'PLANNED'.");
            }

            List<ChampionshipsTeams> championshipTeams = championshipsTeamsRepository.findByChampionshipId(championshipId);
            List<Team> teams = championshipTeams.stream()
                    .map(ChampionshipsTeams::getTeam)
                    .collect(Collectors.toList());

            if (teams.size() < 2) {
                throw new BusinessException("É necessário pelo menos 2 times para gerar partidas.");
            }

            List<Match> generatedMatches = new ArrayList<>();

            switch (championship.getType()) {
                case LEAGUE:
                    generatedMatches.addAll(generateLeagueMatches(championship, teams));
                    break;
                case CUP:
                    generateCupMatches(championship, teams);
                    break;
                default:
                    throw new BusinessException("Tipo de campeonato não suportado para geração de partidas.");
            }

//            if (!generatedMatches.isEmpty()) {
//                championship.setStatus(StatusChampionship.IN_PROGRESS);
//                championshipsRepository.save(championship);
//            }
//            lock.unlock();
            matchRepository.saveAll(generatedMatches);
        } finally {
            // ignore
        }
    }

    /**
     * Gera partidas para o formato de Liga (todos contra todos - turno único).
     */
    private List<Match> generateLeagueMatches(Championships championship, List<Team> teams) {
        List<Match> matches = new ArrayList<>();
        int numTeams = teams.size();

        boolean hasBye = false;
        if (numTeams % 2 != 0) {
            numTeams++;
            hasBye = true;
        }

        List<Team> rotatedTeams = new ArrayList<>(teams);
        if (hasBye) {
            rotatedTeams.add(null);
        }

        int numRounds = numTeams - 1;

        for (int round = 0; round < numRounds; round++) {
            Team homeFixedTeam = rotatedTeams.get(0);

            for (int i = 0; i < numTeams / 2; i++) {
                Team team1 = rotatedTeams.get(i);
                Team team2 = rotatedTeams.get(numTeams - 1 - i);

                if (team1 == null || team2 == null) {
                    System.out.println("Rodada " + (round + 1) + ": Time de folga (Bye) nesta rodada.");
                } else {
                    if (i == 0) {
                        Team fixedTeam = rotatedTeams.get(0);
                        Team rotatingOpponent = rotatedTeams.get(numTeams - 1);
                        if (fixedTeam != null && rotatingOpponent != null) {
                            matches.add(createMatch(championship, fixedTeam, rotatingOpponent, round + 1, LocalDateTime.now().plusDays(((long) (round + 1) * 7L))));
                        }
                    } else {
                        Team teamA = rotatedTeams.get(i);
                        Team teamB = rotatedTeams.get(numTeams - 1 - i);

                        if (teamA != null && teamB != null) {
                            matches.add(createMatch(championship, teamA, teamB, round + 1, LocalDateTime.now().plusDays(((long) (round + 1) * 7L))));
                        }
                    }
                }
            }

            Team lastTeam = rotatedTeams.remove(numTeams - 1);
            rotatedTeams.add(1, lastTeam);
        }

        return matches;
    }

    /**
     * Gera partidas para o formato de Copa (mata-mata).
     * Requer que o número de times seja uma potência de 2.
     */
    private void generateCupMatches(Championships championship, List<Team> teams) {
        int numTeams = teams.size();
        if (numTeams  % 2 != 0 || numTeams < 2) {
            throw new IllegalArgumentException("Para um mata-mata, a quantidade de times deve ser uma potência de 2 (ex: 8, 16, 32...). Times registrados: " + numTeams);
        }

        Collections.shuffle(teams);

        if (championship.getMatchs() != null) {
            matchRepository.deleteAll(championship.getMatchs());
            championship.getMatchs().clear();
        } else {
            championship.setMatchs(new ArrayList<>());
        }

        List<Match> currentRoundMatches = new ArrayList<>();
        int currentRound = 1;

        for (int i = 0; i < numTeams; i += 2) {
            Team homeTeam = teams.get(i);
            Team awayTeam = teams.get(i + 1);

            Match match = createMatch(championship, homeTeam, awayTeam, currentRound, championship.getStartDate().atTime(10, 0).plusDays(currentRound * 7));
            currentRoundMatches.add(match);
        }

        List<Match> previousRoundMatches = new ArrayList<>(currentRoundMatches);

        while (previousRoundMatches.size() > 1) {
            currentRound++;
            List<Match> nextRoundMatches = new ArrayList<>();

            for (int i = 0; i < previousRoundMatches.size(); i += 2) {
                Match nextMatch = Match.builder()
                        .championship(championship)
                        .status(MatchStatus.SCHEDULED)
                        .matchDateTime(championship.getStartDate().atTime(10, 0).plusDays(currentRound * 7))
                        .round(currentRound)
                        .build();
                nextRoundMatches.add(nextMatch);

                Match match1 = previousRoundMatches.get(i);
                Match match2 = previousRoundMatches.get(i + 1);

                match1.setNextMatch(nextMatch);
                match2.setNextMatch(nextMatch);
            }

            matchRepository.saveAll(nextRoundMatches);
            matchRepository.saveAll(previousRoundMatches);

            championship.getMatchs().addAll(nextRoundMatches);
            previousRoundMatches = nextRoundMatches;
        }

        championship.setStatus(StatusChampionship.IN_PROGRESS);
         championshipsRepository.save(championship);
        }



    private Match createMatch(Championships championship, Team homeTeam, Team awayTeam, Integer round, LocalDateTime matchDateTime) {
        return Match.builder()
                .championship(championship)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .round(round)
                .status(MatchStatus.SCHEDULED)
                .matchDateTime(matchDateTime)
                .build();
    }
}
