package br.edu.ifpe.easy_football_management_backend.features.players.command;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Player;
import br.edu.ifpe.easy_football_management_backend.domain.entity.PlayerRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;
import br.edu.ifpe.easy_football_management_backend.domain.entity.TeamRepository;
import br.edu.ifpe.easy_football_management_backend.features.players.PlayerDTO;
import br.edu.ifpe.easy_football_management_backend.features.players.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

//@Component
@Service
public class PlayerCommandHandler {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    private PlayerMapper mapper;

    @Autowired
    public PlayerCommandHandler(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public Player create(PlayerDTO playerDTO, UUID userId) {

        Team team = teamRepository.findFirstClassTeamIdByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));
        System.out.println(team.getName());
        System.out.println("teste");
        Player player = mapper.toEntity(playerDTO);
        player.setTeam(team);

        return playerRepository.save(player);
    }

    public Player update(PlayerDTO playerDTO, UUID playerId) {

        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        existingPlayer.setName(playerDTO.name());
        existingPlayer.setPosition(playerDTO.position());
        existingPlayer.setNumber(playerDTO.number());

        Team team = teamRepository.findById(playerDTO.teamId())
                .orElseThrow(() -> new RuntimeException("Time não encontrado"));

        existingPlayer.setTeam(team);

        return playerRepository.save(existingPlayer);
    }

    public void delete(UUID playerId) {
        playerRepository.deleteById(playerId);
    }
}
