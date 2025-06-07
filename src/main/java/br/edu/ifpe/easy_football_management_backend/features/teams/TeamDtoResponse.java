package br.edu.ifpe.easy_football_management_backend.features.teams;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class TeamDtoResponse {
    public UUID id;

    public String name;

    public String urlImage;
}
