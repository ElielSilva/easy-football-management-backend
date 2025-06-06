package br.edu.ifpe.easy_football_management_backend.features.championshipsteams;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ChampionshipsTeamsDTO(
        @NotNull(message = "id do compeonato é obrigatorio")
        UUID teamId,
        @NotNull(message = "id do compeonato é obrigatorio")
        UUID championshipsId
) {
}
