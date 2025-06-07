package br.edu.ifpe.easy_football_management_backend.features.results.commands;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.BusinessException;
import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.NotFoundException;
import br.edu.ifpe.easy_football_management_backend.domain.entity.*;
import br.edu.ifpe.easy_football_management_backend.features.results.ResultDTO;
import br.edu.ifpe.easy_football_management_backend.features.results.ResultMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
public class ResultCommandHandler {
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
    private final StatisticRepository statisticRepository;
    private final TeamRepository teamRepository;
    private final StandingRepository standingRepository;
    private final ChampionshipsRepository championshipRepository;

    public ResultCommandHandler(ResultRepository resultRepository, UserRepository userRepository,
                                StatisticRepository statisticRepository, TeamRepository teamRepository,
                                StandingRepository standingRepository, ChampionshipsRepository championshipRepository) {
        this.resultRepository = resultRepository;
        this.userRepository = userRepository;
        this.statisticRepository = statisticRepository;
        this.teamRepository = teamRepository;
        this.standingRepository = standingRepository;
        this.championshipRepository = championshipRepository;
    }

    @Transactional
    public ResultDTO handle(ResultDTO resultDto) {
        Result resultEntity;

        if (resultDto.resultId != null) {
            resultEntity = resultRepository.findById(resultDto.resultId)
                    .orElseThrow(() -> new NotFoundException("Result not found with ID: " + resultDto.resultId));
            resultEntity.setHomeTeamGoals(resultDto.homeTeamGoals);
            resultEntity.setAwayTeamGoals(resultDto.awayTeamGoals);
        } else {
            resultEntity = ResultMapper.mapToNewResult(resultDto);
            Championships championship = championshipRepository.findById(resultDto.championshipId)
                    .orElseThrow(() -> new NotFoundException("Championship not found with ID: " + resultDto.championshipId));
            resultEntity.setChampionship(championship);

            resultEntity.setStatus(STATUS.FINISHED);
        }


        List<Statistic> statistics = ResultMapper.mapToStatistics(resultDto, resultEntity);
        resultEntity.setStatistics(statistics);

        resultRepository.save(resultEntity);

        updateStandings(resultEntity);

        return ResultMapper.mapToResultDTO(resultEntity);
    }

    private void updateStandings(Result result) {
        var homeTeam = teamRepository.findById(result.getIdHomeTeam())
                .orElseThrow(() -> new NotFoundException("Home team not found with ID: " + result.getIdHomeTeam()));
        var awayTeam = teamRepository.findById(result.getIdAwayTeam())
                .orElseThrow(() -> new NotFoundException("Away team not found with ID: " + result.getIdAwayTeam()));

        int homeGoals = result.getHomeTeamGoals();
        int awayGoals = result.getAwayTeamGoals();
        Championships championship = result.getChampionship();

        updateStandingForTeam(championship, homeTeam, homeGoals, awayGoals);
        updateStandingForTeam(championship, awayTeam, awayGoals, homeGoals);
    }

    private void updateStandingForTeam(Championships championship, Team team, int goalsFor, int goalsAgainst) {
        Standing standing = standingRepository.findByChampionshipAndTeam(championship, team);
        if (standing == null) {
            standing = new Standing();
            standing.setChampionship(championship);
            standing.setTeam(team);
            standing.setPoints(0);
            standing.setWins(0);
            standing.setDraws(0);
            standing.setLosses(0);
            standing.setGoalsFor(0);
            standing.setGoalsAgainst(0);
            standing.setGoalDifference(0);
        }

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
