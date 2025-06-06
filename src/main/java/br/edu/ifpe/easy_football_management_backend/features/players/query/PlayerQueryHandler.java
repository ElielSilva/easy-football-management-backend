package br.edu.ifpe.easy_football_management_backend.features.players.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Player;
import br.edu.ifpe.easy_football_management_backend.domain.entity.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerQueryHandler {
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerQueryHandler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    public List<Player> getAllForTeamId(UUID TeamId) {
        return playerRepository.getAllPlayersForTeamId(TeamId);
    }


}
