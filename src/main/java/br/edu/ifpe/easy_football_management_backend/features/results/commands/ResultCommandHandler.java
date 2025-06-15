package br.edu.ifpe.easy_football_management_backend.features.results.commands;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.BusinessException;
import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import br.edu.ifpe.easy_football_management_backend.features.results.PlayerResultDTO;
import br.edu.ifpe.easy_football_management_backend.features.results.ResultDTO;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ResultCommandHandler {

    private final MatchRepository matchRepository;
    private final StandingRepository standingRepository;
    private final StatisticRepository statisticRepository;
    private final PlayerRepository playerRepository; // Injetado para buscar jogadores

    public ResultCommandHandler(MatchRepository matchRepository,
                         StandingRepository standingRepository,
                         StatisticRepository statisticRepository,
                         PlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.standingRepository = standingRepository;
        this.statisticRepository = statisticRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public Match recordMatchResult(ResultDTO resultDTO) {
        // 1. Validação inicial do DTO (via Jakarta Validation nas anotações do DTO)
        // Spring MVC/WebFlux fará a validação @Valid no controller.

        // 2. Buscar a partida
        Match match = matchRepository.findById(resultDTO.resultId)
                .orElseThrow(() -> new BusinessException("Partida não encontrada com ID: " + resultDTO.resultId));

        // Valida se os IDs dos times no DTO correspondem aos da partida
        if (!match.getHomeTeam().getId().equals(resultDTO.idHomeTeam) ||
                !match.getAwayTeam().getId().equals(resultDTO.idAwayTeam)) {
            throw new BusinessException("Os IDs dos times no resultado não correspondem aos da partida.");
        }
        if (!match.getChampionship().getId().equals(resultDTO.championshipId)) {
            throw new BusinessException("O ID do campeonato no resultado não corresponde ao da partida.");
        }

        if (!match.getStatus().equals(MatchStatus.SCHEDULED) && !match.getStatus().equals(MatchStatus.IN_PROGRESS)) {
            throw new BusinessException("Não é possível registrar resultado para uma partida que não está agendada ou em andamento.");
        }

        // 3. Atualizar os gols e o status da Match
        match.setHomeTeamGoals(resultDTO.homeTeamGoals);
        match.setAwayTeamGoals(resultDTO.awayTeamGoals);
        match.setStatus(MatchStatus.COMPLETED); // Partida finalizada

        Match updatedMatch = matchRepository.save(match); // Salva as alterações na partida

        // 4. Atualizar a Standing (classificação)
        updateStandings(match);

        // 5. Processar as Statistics dos jogadores
        if (resultDTO.playersResults != null && !resultDTO.playersResults.isEmpty()) {
            processPlayerStatistics(match, resultDTO.playersResults);
        }

        System.out.println("Resultado registrado, classificações e estatísticas atualizadas para a partida: " + resultDTO.resultId);
        return updatedMatch;
    }

    private void updateStandings(Match match) {
        Championships championship = match.getChampionship();
        Team homeTeam = match.getHomeTeam();
        Team awayTeam = match.getAwayTeam();

        Standing homeStanding = standingRepository.findByChampionshipIdAndTeamId(championship.getId(), homeTeam.getId())
                .orElseGet(() -> createNewStanding(championship, homeTeam));
        Standing awayStanding = standingRepository.findByChampionshipIdAndTeamId(championship.getId(), awayTeam.getId())
                .orElseGet(() -> createNewStanding(championship, awayTeam));

        homeStanding.setGamesPlayed(homeStanding.getGamesPlayed() + 1);
        awayStanding.setGamesPlayed(awayStanding.getGamesPlayed() + 1);

        homeStanding.setGoalsFor(homeStanding.getGoalsFor() + match.getHomeTeamGoals());
        homeStanding.setGoalsAgainst(homeStanding.getGoalsAgainst() + match.getAwayTeamGoals());
        homeStanding.setGoalDifference(homeStanding.getGoalsFor() - homeStanding.getGoalsAgainst());

        awayStanding.setGoalsFor(awayStanding.getGoalsFor() + match.getAwayTeamGoals());
        awayStanding.setGoalsAgainst(awayStanding.getGoalsAgainst() + match.getHomeTeamGoals());
        awayStanding.setGoalDifference(awayStanding.getGoalsFor() - awayStanding.getGoalsAgainst());

        if (match.getHomeTeamGoals() > match.getAwayTeamGoals()) {
            homeStanding.setWins(homeStanding.getWins() + 1);
            homeStanding.setPoints(homeStanding.getPoints() + 3);
            awayStanding.setLosses(awayStanding.getLosses() + 1);
        } else if (match.getAwayTeamGoals() > match.getHomeTeamGoals()) {
            awayStanding.setWins(awayStanding.getWins() + 1);
            awayStanding.setPoints(awayStanding.getPoints() + 3);
            homeStanding.setLosses(homeStanding.getLosses() + 1);
        } else {
            homeStanding.setDraws(homeStanding.getDraws() + 1);
            homeStanding.setPoints(homeStanding.getPoints() + 1);
            awayStanding.setDraws(awayStanding.getDraws() + 1);
            awayStanding.setPoints(awayStanding.getPoints() + 1);
        }

        homeStanding.setUpdatedAt(LocalDateTime.now());
        awayStanding.setUpdatedAt(LocalDateTime.now());

        standingRepository.save(homeStanding);
        standingRepository.save(awayStanding);
    }

    private Standing createNewStanding(Championships championship, Team team) {
        return Standing.builder()
                .championship(championship)
                .team(team)
                .points(0)
                .wins(0)
                .draws(0)
                .losses(0)
                .goalsFor(0)
                .goalsAgainst(0)
                .goalDifference(0)
                .gamesPlayed(0)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    /**
     * Processa e salva as estatísticas individuais dos jogadores para a partida.
     */
    private void processPlayerStatistics(Match match, List<PlayerResultDTO> playersResults) {
        for (PlayerResultDTO playerDto : playersResults) {
            Player player = playerRepository.findById(playerDto.playerId)
                    .orElseThrow(() -> new BusinessException("Jogador não encontrado com ID: " + playerDto.playerId));

            if (!player.getTeam().getId().equals(match.getHomeTeam().getId()) &&
                    !player.getTeam().getId().equals(match.getAwayTeam().getId())) {
                System.out.println("Aviso: Jogador " + player.getName() + " (ID: " + player.getId() + ") não pertence aos times da partida " + match.getId() + ". Estatísticas ignoradas.");
                continue; // Pular este jogador se não for dos times da partida
            }


            Statistic statistic = Statistic.builder()
                    .player(player)
                    .match(match)
                    .goals(playerDto.goals)
                    .yellowCards(playerDto.yellowCards)
                    .redCards(playerDto.redCards)
                    .build();

            statisticRepository.save(statistic);
            System.out.println("Estatística salva para jogador " + player.getName() + " na partida " + match.getId());
        }
    }
}