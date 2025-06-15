package br.edu.ifpe.easy_football_management_backend.features.classification.command;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.BusinessException;
import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.NotFoundException;
import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import org.apache.coyote.BadRequestException;
import org.redisson.api.RedissonClient;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
        String keyLock = "ClassificationHandler.GenerateClassificationEvent.";
        var lock = redissonClient.getLock(keyLock);
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

            if (!generatedMatches.isEmpty()) {
                championship.setStatus(StatusChampionship.IN_PROGRESS);
                championshipsRepository.save(championship);
            }

            matchRepository.saveAll(generatedMatches);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gera partidas para o formato de Liga (todos contra todos - turno único).
     */
    private List<Match> generateLeagueMatches(Championships championship, List<Team> teams) {
        List<Match> matches = new ArrayList<>();
        int numTeams = teams.size();
        int currentRound = 1;

        for (int i = 0; i < numTeams - 1; i++) {
            for (int j = i + 1; j < numTeams; j++) {
                Team homeTeam = teams.get(i);
                Team awayTeam = teams.get(j);

                matches.add(createMatch(championship, homeTeam, awayTeam, currentRound, LocalDateTime.now().plusDays(currentRound * 7L))); // Exemplo: 1 semana por rodada
            }
            currentRound++;
        }
        return matches;
    }

    /**
     * Gera partidas para o formato de Copa (mata-mata).
     * Requer que o número de times seja uma potência de 2.
     */
    private List<Match> generateCupMatches(Championships championship, List<Team> teams) {
        List<Match> matches = new ArrayList<>();
        int numTeams = teams.size();

        if (numTeams == 0 || (numTeams & (numTeams - 1)) != 0) {
            throw new BusinessException("Para campeonatos de copa, o número de times deve ser uma potência de 2 (ex: 2, 4, 8, 16...). Atualmente: " + numTeams);
        }

        Collections.shuffle(teams);

        int round = 1;
        List<Team> currentRoundTeams = new ArrayList<>(teams);

        while (currentRoundTeams.size() > 1) {
            if (currentRoundTeams.size() % 2 != 0) {
                throw new BusinessException("Número ímpar de times na rodada de copa. Erro de lógica.");
            }

            List<Team> nextRoundTeams = new ArrayList<>();

            for (int i = 0; i < currentRoundTeams.size(); i += 2) {
                Team homeTeam = currentRoundTeams.get(i);
                Team awayTeam = currentRoundTeams.get(i + 1);

                matches.add(createMatch(championship, homeTeam, awayTeam, round, LocalDateTime.now().plusDays(round * 3L))); // Exemplo: 3 dias entre rodadas

            }

            break;
        }
        return matches;
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
