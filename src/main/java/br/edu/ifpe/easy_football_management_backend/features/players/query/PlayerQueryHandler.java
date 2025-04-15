package br.edu.ifpe.easy_football_management_backend.features.players.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Player;
import br.edu.ifpe.easy_football_management_backend.domain.entity.PlayerRepository;

import java.util.List;

public class PlayerQueryHandler {
    private PlayerRepository playerRepository;

    public List<Player> getAll() {
        return playerRepository.findAll();
    }

}
