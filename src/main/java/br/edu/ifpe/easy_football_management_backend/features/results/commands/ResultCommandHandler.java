package br.edu.ifpe.easy_football_management_backend.features.results.commands;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.BusinessException;
import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import br.edu.ifpe.easy_football_management_backend.features.results.ResultDTO;
import br.edu.ifpe.easy_football_management_backend.features.results.ResultMapper;
import org.springframework.stereotype.Component;
import java.util.UUID;


@Component
public class ResultCommandHandler {
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
    private final StatisticRepository statisticRepository;
    private final TeamRepository teamRepository;
    private final StandingRepository standingRepository;


    public ResultCommandHandler(ResultRepository resultRepository, UserRepository userRepository, StatisticRepository statisticRepository, TeamRepository teamRepository, StandingRepository standingRepository) {
        this.resultRepository = resultRepository;
        this.userRepository = userRepository;
        this.statisticRepository = statisticRepository;
        this.teamRepository = teamRepository;
        this.standingRepository = standingRepository;
    }

    public ResultDTO handle(ResultDTO result) {
        UUID resultId = result.ResultId;
        var resultEntity = resultRepository.findById(resultId);

        var statisticList = ResultMapper.mapToResult(result);

        if (resultEntity.isEmpty())
            throw new BusinessException("Result not found");

        resultEntity.get().setStatus(STATUS.IN_PROGRESS);
        resultEntity.get().setStatistics(statisticList);

        resultRepository.save(resultEntity.get());

        return result;
    }

    private void updateStandings(Result result) {
        var home = teamRepository.findById(result.getIdHomeTeam())
                .orElseThrow(() -> new BusinessException("Home team not found"));
        Team away = teamRepository.findById(result.getIdAwayTeam())
                .orElseThrow(() -> new BusinessException("Home team not found"));
        int homeGoals = result.getHomeTeamGoals();
        int awayGoals = result.getAwayTeamGoals();
        Championships championship = result.getChampionship();

        updateStandingForTeam(championship, home, homeGoals, awayGoals);
        updateStandingForTeam(championship, away, awayGoals, homeGoals);
    }

    private void updateStandingForTeam(Championships championship, Team team, int goalsFor, int goalsAgainst) {
        Standing standing = standingRepository.findByChampionshipAndTeam(championship, team);
        if (standing == null) {
           throw new BusinessException("Standing not found");
        }
        
        standing.setChampionship(championship);
        standing.setTeam(team);
        standing.setGoalsFor(standing.getGoalsFor() + goalsFor);
        standing.setGoalsAgainst(standing.getGoalsAgainst() + goalsAgainst);
        standing.setGoalDifference(standing.getGoalsFor() - standing.getGoalsAgainst());

        if (goalsFor > goalsAgainst) {
            standing.setWins(standing.getWins() + 1);
            standing.setPoints(standing.getPoints() + 3);
        } else if (goalsFor == goalsAgainst) {
            standing.setDraws(standing.getDraws() + 1);
            standing.setPoints(standing.getPoints() + 1);
        } else {
            standing.setLosses(standing.getLosses() + 1);
        }
        standingRepository.save(standing);
    }
}
