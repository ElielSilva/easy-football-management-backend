package br.edu.ifpe.easy_football_management_backend.features.results;

import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public class ResultDTO {
    @NotBlank(message = "ResultId is required")
    public UUID ResultId;
    public List<PlayerResultDTO> playersResults;
}


