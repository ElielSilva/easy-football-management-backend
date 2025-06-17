package br.edu.ifpe.easy_football_management_backend.features.championshipsteams;

import br.edu.ifpe.easy_football_management_backend.domain.entity.ChampionshipsTeams;
import br.edu.ifpe.easy_football_management_backend.features.championshipsteams.command.ChampionshipsTeamsCommandHandller;
import br.edu.ifpe.easy_football_management_backend.features.championshipsteams.query.ChampionshipsTeamsQueryHandller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/championshipsteams")
@SecurityRequirement(name = "bearerAuth")
public class ChampionshipsTeamsController {
    private final ChampionshipsTeamsCommandHandller championshipsTeamsCommandHandller;
    private final ChampionshipsTeamsQueryHandller championshipsTeamsQueryHandller;

    public ChampionshipsTeamsController(ChampionshipsTeamsCommandHandller championshipsTeamsCommandHandller, ChampionshipsTeamsQueryHandller championshipsTeamsQueryHandller) {
        this.championshipsTeamsCommandHandller = championshipsTeamsCommandHandller;
        this.championshipsTeamsQueryHandller = championshipsTeamsQueryHandller;
    }

    @GetMapping("/{ChampionshipsTeamsId}")
    public ResponseEntity<String> FindById(@RequestHeader("Authorization") String authHeader, @PathVariable("ChampionshipsTeamsId") String tourmanetId) {
        return ResponseEntity.ok(tourmanetId);
    }

    @GetMapping("")
    public ResponseEntity<String> FindAll() {
        return ResponseEntity.ok("sucesso");
    }

    @GetMapping("isExists")
    public ResponseEntity<String> registerTeam(@RequestHeader("Authorization") String authHeader, @RequestParam UUID championshipId, @RequestParam UUID teamId) {
        championshipsTeamsQueryHandller.getOne(authHeader, championshipId, teamId);
        // Aqui você pode usar os IDs normalmente
        return ResponseEntity.ok("Exists");
    }


    @PostMapping("")
    public ResponseEntity<ChampionshipsTeams> create(@RequestHeader("Authorization") String authHeader, @RequestBody @Valid ChampionshipsTeamsDTO championshipsTeamsDTO) {
        ChampionshipsTeams championshipsTeamsSaved = championshipsTeamsCommandHandller.create(authHeader, championshipsTeamsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(championshipsTeamsSaved);
    }

    @DeleteMapping("")
    public ResponseEntity<String> delete(@RequestHeader("Authorization") String authHeader, @RequestBody @Valid ChampionshipsTeamsDTO championshipsTeamsDTO) {
        championshipsTeamsCommandHandller.deleteTournamentsTeams(authHeader, championshipsTeamsDTO.championshipsId());
        return ResponseEntity.ok("sucesso");
    }
}
