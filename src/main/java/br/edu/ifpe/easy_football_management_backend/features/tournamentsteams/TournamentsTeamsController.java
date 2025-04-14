package br.edu.ifpe.easy_football_management_backend.features.tournamentsteams;

import br.edu.ifpe.easy_football_management_backend.features.tournamentsteams.command.TournamentsTeamsCommandHandller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tournamentsteams")
public class TournamentsTeamsController {
    private final TournamentsTeamsCommandHandller tournamentsTeamsCommandHandller;

    public TournamentsTeamsController(TournamentsTeamsCommandHandller tournamentsTeamsCommandHandller) {
        this.tournamentsTeamsCommandHandller = tournamentsTeamsCommandHandller;
    }

    @GetMapping("/subscribe/{tourmanetId}")
    public ResponseEntity<String> FindAllTournamentsTeamsController(@RequestHeader("Authorization") String authHeader, @PathVariable("tourmanetId") String tourmanetId) {
        return ResponseEntity.ok(tourmanetId);
    }

    @PostMapping("/subscribe/{tourmanetId}")
    public ResponseEntity<String> createdTournamentsTeamsController(@RequestHeader("Authorization") String authHeader, @PathVariable("tourmanetId") String tourmanetId) {
        return ResponseEntity.ok(tourmanetId);
    }

    @DeleteMapping("/subscribe/{tourmanetId}")
    public ResponseEntity<String> deleteTournamentsTeams( @RequestHeader("Authorization") String authHeader, @PathVariable("tourmanetId") String tourmanetId) {
        return ResponseEntity.ok(tourmanetId);
    }
}
