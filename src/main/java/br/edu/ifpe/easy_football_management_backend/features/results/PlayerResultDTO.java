package br.edu.ifpe.easy_football_management_backend.features.results;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class PlayerResultDTO {
    @NotBlank(message = "playerId is required")
    public UUID playerId;
    public Integer goals;
    public Integer yellowCards;
    public Integer redCards;
    public Integer participations;
    public Integer goalAgainst;
}
