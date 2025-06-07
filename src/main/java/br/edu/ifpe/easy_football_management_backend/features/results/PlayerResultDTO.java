package br.edu.ifpe.easy_football_management_backend.features.results;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class PlayerResultDTO {
    @NotNull(message = "playerId is required")
    public UUID playerId;
    public Integer goals = 0;
    public Integer yellowCards = 0;
    public Integer redCards = 0;
    public Integer participations = 0;
    public Integer goalAgainst = 0;
}
