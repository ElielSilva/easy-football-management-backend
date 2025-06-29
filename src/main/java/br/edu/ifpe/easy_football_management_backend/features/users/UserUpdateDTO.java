package br.edu.ifpe.easy_football_management_backend.features.users;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(@NotNull String name,  String email, String phone, String urlImage) {
}
