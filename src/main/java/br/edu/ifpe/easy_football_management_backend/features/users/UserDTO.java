package br.edu.ifpe.easy_football_management_backend.features.users;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserDTO(
        UUID id,
        @NotNull @Min(3) @Max(100) String name,
        @NotNull String email,
        String phone,
        String urlImage,
        Team team
) {
}

