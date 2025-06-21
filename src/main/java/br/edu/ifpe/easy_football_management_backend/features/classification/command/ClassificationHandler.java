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
                    generatedMatches.addAll(generateCupMatches(championship, teams));
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
    private List<Match> generateCupMatches(Championships championship, List<Team> teams) {
       // gere a arvore aqui..

        return new ArrayList<>();
    }

    private Match createMatch(Championships championship, Team homeTeam, Team awayTeam, Integer round, LocalDateTime matchDateTime) {
        return Match.builder()
                .championship(championship)
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .round(round)
                .status(MatchStatus.SCHEDULED) // Status inicial da partida
                .matchDateTime(matchDateTime)
                .build();
    }
}
