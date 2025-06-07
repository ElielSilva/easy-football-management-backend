package br.edu.ifpe.easy_football_management_backend.features.players.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Player;
import br.edu.ifpe.easy_football_management_backend.domain.entity.PlayerRepository;
import br.edu.ifpe.easy_football_management_backend.features.players.PlayerDtoResponse;
import br.edu.ifpe.easy_football_management_backend.features.players.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerQueryHandler {
    private PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Autowired
    public PlayerQueryHandler(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public List<PlayerDtoResponse> getAll() {
        return playerRepository
                .findAll()
                .stream()
                .map(playerMapper::toDtoResponse)
                .toList();
    }

    public List<Player> getAllForTeamId(UUID TeamId) {
        return playerRepository
                .getAllPlayersForTeamId(TeamId);
    }

}
