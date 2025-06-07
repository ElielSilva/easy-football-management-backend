package br.edu.ifpe.easy_football_management_backend.features.players;

import br.edu.ifpe.easy_football_management_backend.features.teams.TeamDtoResponse;


import java.util.UUID;

public class PlayerDtoResponse {
    public UUID id;
    public String name;
    public String position;
    public Integer number;
    public TeamDtoResponse team;
}
