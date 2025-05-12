package br.edu.ifpe.easy_football_management_backend.features.players;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Player;
import br.edu.ifpe.easy_football_management_backend.domain.entity.PlayerRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public PlayerMapper(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public Player toEntity(PlayerDTO dto) {
        return Player.builder()
                .name(dto.name())
                .position(dto.position())
                .number(dto.number())
                .build();
    }

    public PlayerDTO toDTO(Player entity) {
        return new PlayerDTO(
                entity.getName(),
                entity.getPosition(),
                entity.getNumber(),
                entity.getTeam().getId()
        );
    }
}