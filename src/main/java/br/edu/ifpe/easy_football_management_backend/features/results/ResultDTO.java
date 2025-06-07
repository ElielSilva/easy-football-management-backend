package br.edu.ifpe.easy_football_management_backend.features.results;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class ResultDTO {
    public UUID resultId;
    @NotNull(message = "HomeTeamId is required")
    public UUID idHomeTeam;
    @NotNull(message = "HomeTeamGoals are required")
    public Integer homeTeamGoals;
    @NotNull(message = "AwayTeamId is required")
    public UUID idAwayTeam;
    @NotNull(message = "AwayTeamGoals are required")
    public Integer awayTeamGoals;
    @NotNull(message = "ChampionshipId is required")
    public UUID championshipId;
    public List<PlayerResultDTO> playersResults;
}


