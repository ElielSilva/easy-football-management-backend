package br.edu.ifpe.easy_football_management_backend.features.players;

import br.edu.ifpe.easy_football_management_backend.domain.entity.PlayerPosition;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PlayerDTO(
        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Posição é obrigatória")
        PlayerPosition position,

        @NotNull(message = "Número é obrigatório")
        @Min(value = 1, message = "Número deve ser maior que zero")
        @Max(value = 99, message = "Número deve ser menor que 100")
        Integer number,

        @NotNull(message = "ID do time é obrigatório")
        UUID teamId
) {
}
