package br.edu.ifpe.easy_football_management_backend.features.championships;

import br.edu.ifpe.easy_football_management_backend.domain.entity.StatusChampionship;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TypeChampionship;

import java.util.UUID;

public record ChampionshipsDTO(
        String name,
        String description,
        String img,
        int quantityTeams,
        StatusChampionship statusChampionship,
        TypeChampionship typeChampionship,
        UUID userID
) {
}
