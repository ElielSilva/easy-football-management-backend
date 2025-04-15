package br.edu.ifpe.easy_football_management_backend.features.players;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Player;
import br.edu.ifpe.easy_football_management_backend.features.players.query.PlayerQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayersController {
    private PlayerQueryHandler playerQueryHandler;

//    @PostMapping("")
//    public void create(@RequestBody PlayerDTO) {
//
//    }

    @GetMapping("")
    public ResponseEntity<List<Player>> getAll() {
        List<Player> players = playerQueryHandler.getAll();
        return ResponseEntity.ok(players);
    }
}
