package br.edu.ifpe.easy_football_management_backend.features.teams;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TeamDTO(@NotNull @Min(3) @Max(100) String name, String img, @NotNull @NotEmpty UUID userID) {

}
