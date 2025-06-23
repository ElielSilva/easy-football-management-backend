package br.edu.ifpe.easy_football_management_backend.features.championships;

import br.edu.ifpe.easy_football_management_backend.domain.entity.StatusChampionship;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TypeChampionship;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
                               @DecimalMax(value = "9999999999.99", message = "O prêmio não pode ser maior que 9999999999.99")
                               @DecimalMin(value = "0.01", message = "O prêmio deve ser pelo menos 0.01")
                               double award) {
}
