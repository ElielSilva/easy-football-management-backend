package br.edu.ifpe.easy_football_management_backend.features.championshipsteams;

import br.edu.ifpe.easy_football_management_backend.features.championshipsteams.command.ChampionshipsTeamsCommandHandller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/championshipsteams")
public class ChampionshipsTeamsController {
    private final ChampionshipsTeamsCommandHandller tournamentsTeamsCommandHandller;

    public ChampionshipsTeamsController(ChampionshipsTeamsCommandHandller tournamentsTeamsCommandHandller) {
        this.tournamentsTeamsCommandHandller = tournamentsTeamsCommandHandller;
    }

    @GetMapping("/subscribe/{ChampionshipsId}")
    public ResponseEntity<String> FindAll(@RequestHeader("Authorization") String authHeader, @PathVariable("ChampionshipsId") String tourmanetId) {
        return ResponseEntity.ok(tourmanetId);
    }

    @PostMapping("/subscribe/{ChampionshipsId}")
    public ResponseEntity<String> created(@RequestHeader("Authorization") String authHeader, @PathVariable("ChampionshipsId") String tourmanetId) {
        tournamentsTeamsCommandHandller.createdTournamentsTeams(authHeader, tourmanetId);
        return ResponseEntity.ok(tourmanetId);
    }

    @DeleteMapping("/subscribe/{ChampionshipsId}")
    public ResponseEntity<String> delete( @RequestHeader("Authorization") String authHeader, @PathVariable("ChampionshipsId") String tourmanetId) {
        return ResponseEntity.ok(tourmanetId);
    }
}
