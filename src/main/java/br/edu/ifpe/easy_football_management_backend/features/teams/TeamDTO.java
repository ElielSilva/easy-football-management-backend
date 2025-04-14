package br.edu.ifpe.easy_football_management_backend.features.teams;

import java.util.UUID;

public record TeamDTO(String name, String img, UUID userID) {

}
