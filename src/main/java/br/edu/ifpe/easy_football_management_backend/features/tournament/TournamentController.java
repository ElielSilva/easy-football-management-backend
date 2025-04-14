package br.edu.ifpe.easy_football_management_backend.features.tournament;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Tournament;
import br.edu.ifpe.easy_football_management_backend.features.tournament.command.TournamentCommandHanhller;
import br.edu.ifpe.easy_football_management_backend.features.tournament.query.TournamentQueryHandller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    private final TournamentQueryHandller tournamentQueryHandller;
    private final TournamentCommandHanhller tournamentCommandHanhller;

    public TournamentController(TournamentQueryHandller tournamentQueryHandller, TournamentCommandHanhller tournamentCommandHanhller) {
        this.tournamentQueryHandller = tournamentQueryHandller;
        this.tournamentCommandHanhller = tournamentCommandHanhller;
    }

    @PostMapping("/create")
    public ResponseEntity createTournament(@RequestHeader("Authorization") String authHeader, @RequestBody(required = false) TournamentCreateDTO tournament) {
//        if (tournament == null) {
//            return ResponseEntity.badRequest().body("Torneio não recebido no corpo da requisição.");
//        }
        tournamentCommandHanhller.createTournament(authHeader, tournament);
//        return ResponseEntity.ok(tournaments);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tournament>> findAllTournament() {
        List<Tournament> tournaments = tournamentQueryHandller.findAllTournament();
        return ResponseEntity.ok(tournaments);
//        return ResponseEntity.ok("sucesso");
    }
}
