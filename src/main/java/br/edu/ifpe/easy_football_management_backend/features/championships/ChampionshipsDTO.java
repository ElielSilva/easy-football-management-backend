package br.edu.ifpe.easy_football_management_backend.features.championships;

import br.edu.ifpe.easy_football_management_backend.domain.entity.StatusChampionship;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TypeChampionship;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.UUID;

public record ChampionshipsDTO(@NotNull
                               @Size(min = 3, max = 50)
                               String name,
                               @Size(max = 500)
                               String description,
                               String img,
                               int quantityTeams,
                               @NotNull
                               StatusChampionship statusChampionship,
                               @NotNull
                               TypeChampionship typeChampionship,
                               UUID userID,
                               float award) {
}
