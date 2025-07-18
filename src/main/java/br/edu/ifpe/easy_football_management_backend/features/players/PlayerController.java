package br.edu.ifpe.easy_football_management_backend.features.players;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Player;
import br.edu.ifpe.easy_football_management_backend.features.players.command.PlayerCommandHandler;
import br.edu.ifpe.easy_football_management_backend.features.players.query.PlayerQueryHandler;
import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {
    private final PlayerCommandHandler playerCommandHandler;
    private final PlayerQueryHandler playerQueryHandler;

    public PlayerController(PlayerCommandHandler playerCommandHandler, PlayerQueryHandler playerQueryHandler) {
        this.playerCommandHandler = playerCommandHandler;
        this.playerQueryHandler = playerQueryHandler;
    }


    @PostMapping("")
    public ResponseEntity<Player> create(@RequestBody @Valid PlayerDTO playerDTO, @RequestHeader("Authorization") String token) {
        var userId = UUID.fromString(TokenService.extract(token));
        var player = playerCommandHandler.create(playerDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(player);
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Player>> getAll(@PathVariable("teamId") UUID teamId) {
        System.out.println(teamId);
        List<Player> players = playerQueryHandler.getAllForTeamId(teamId);
        return ResponseEntity.ok(players);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlayerDtoResponse>> get() {
        var players = playerQueryHandler.getAll();
        return ResponseEntity.ok(players);
    }

    @PutMapping("{playerId}")
    public ResponseEntity<Player> update(@RequestBody @Valid PlayerDTO playerDTO, @PathVariable("playerId") UUID playerId) {

        Player p = playerCommandHandler.update(playerDTO, playerId);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("{playerId}")
    public ResponseEntity<Void> delete(@PathVariable UUID playerId) {
        playerCommandHandler.delete(playerId);
        return ResponseEntity.noContent().build();
    }
}
