package br.edu.ifpe.easy_football_management_backend.features.users;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String email,
        String phone,
        String urlImage,
        Team team
) {
}

