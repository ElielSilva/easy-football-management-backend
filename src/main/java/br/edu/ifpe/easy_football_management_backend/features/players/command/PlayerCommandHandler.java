package br.edu.ifpe.easy_football_management_backend.features.players.command;

import br.edu.ifpe.easy_football_management_backend.application.commom.exceptions.NotFoundException;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Player;
import br.edu.ifpe.easy_football_management_backend.domain.entity.PlayerRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TeamRepository;
import br.edu.ifpe.easy_football_management_backend.features.players.PlayerDTO;
import br.edu.ifpe.easy_football_management_backend.features.players.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerCommandHandler {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    private final PlayerMapper mapper;

    @Autowired
    public PlayerCommandHandler(
            PlayerRepository playerRepository,
            TeamRepository teamRepository,
            PlayerMapper mapper) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.mapper = mapper;
    }

    public Player create(PlayerDTO playerDTO, UUID userId) {
        System.out.println(userId);
        Team team = teamRepository.findFirstClassTeamIdByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Team not found for user ID: " + userId));
        Player player = mapper.toEntity(playerDTO);
        player.setTeam(team);

        return playerRepository.save(player);
    }

    public Player update(PlayerDTO playerDTO, UUID playerId) {

        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(() -> new NotFoundException("Player not found with ID: " + playerId));
        existingPlayer.setName(playerDTO.name());
        existingPlayer.setPosition(playerDTO.position());
        existingPlayer.setNumber(playerDTO.number());
        Team team = teamRepository.findById(playerDTO.teamId())
                .orElseThrow(() -> new NotFoundException("Team not found with ID: " + playerDTO.teamId()));

        existingPlayer.setTeam(team);

        return playerRepository.save(existingPlayer);
    }

    public void delete(UUID playerId) {
        playerRepository.deleteById(playerId);
    }
}
