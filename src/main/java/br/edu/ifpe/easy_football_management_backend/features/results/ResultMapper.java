package br.edu.ifpe.easy_football_management_backend.features.results;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Player;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Result;
import br.edu.ifpe.easy_football_management_backend.domain.entity.STATUS;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Statistic;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ResultMapper {

    public static List<Statistic> mapToStatistics(ResultDTO resultDto, Result resultEntity) {
        return resultDto.playersResults.stream().map(playerResultDto ->
                Statistic.builder()
                        .id(UUID.randomUUID())
                        .player(Player.builder().id(playerResultDto.playerId).build())
                        .goals(playerResultDto.goals)
                        .redCards(playerResultDto.redCards)
                        .yellowCards(playerResultDto.yellowCards)
                        .build()
        ).collect(Collectors.toList());
    }

    public static Result mapToNewResult(ResultDTO resultDto) {
        return Result.builder()
                .idHomeTeam(resultDto.idHomeTeam)
                .homeTeamGoals(resultDto.homeTeamGoals)
                .idAwayTeam(resultDto.idAwayTeam)
                .awayTeamGoals(resultDto.awayTeamGoals)
                .status(STATUS.FINISHED)
                .build();
    }

    public static ResultDTO mapToResultDTO(Result resultEntity) {
        ResultDTO dto = new ResultDTO();
        dto.resultId = resultEntity.getId();
        dto.idHomeTeam = resultEntity.getIdHomeTeam();
        dto.homeTeamGoals = resultEntity.getHomeTeamGoals();
        dto.idAwayTeam = resultEntity.getIdAwayTeam();
        dto.awayTeamGoals = resultEntity.getAwayTeamGoals();
        dto.championshipId = resultEntity.getChampionship() != null ? resultEntity.getChampionship().getId() : null;
        return dto;
    }
}
