package br.edu.ifpe.easy_football_management_backend.features.classification;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClassificationDTO(@NotEmpty  @NotNull UUID championshipId) {
}
