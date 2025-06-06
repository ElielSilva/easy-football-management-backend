package br.edu.ifpe.easy_football_management_backend.features.users;

public record UserUpdateDTO(String name, String email, String password, String phone, String urlImage) {
}
