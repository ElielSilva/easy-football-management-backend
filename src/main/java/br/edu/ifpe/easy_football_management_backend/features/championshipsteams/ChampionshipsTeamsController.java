package br.edu.ifpe.easy_football_management_backend.features.championshipsteams;

import br.edu.ifpe.easy_football_management_backend.domain.entity.ChampionshipsTeams;
import br.edu.ifpe.easy_football_management_backend.features.championshipsteams.command.ChampionshipsTeamsCommandHandller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/championshipsteams")
@SecurityRequirement(name = "bearerAuth")
public class ChampionshipsTeamsController {
    private final ChampionshipsTeamsCommandHandller championshipsTeamsCommandHandller;

    public ChampionshipsTeamsController(ChampionshipsTeamsCommandHandller championshipsTeamsCommandHandller) {
        this.championshipsTeamsCommandHandller = championshipsTeamsCommandHandller;
    }

    @GetMapping("/{ChampionshipsTeamsId}")
    public ResponseEntity<String> FindById(@RequestHeader("Authorization") String authHeader, @PathVariable("ChampionshipsTeamsId") String tourmanetId) {
        return ResponseEntity.ok(tourmanetId);
    }

    @GetMapping("")
    public ResponseEntity<String> FindAll() {
        return ResponseEntity.ok("sucesso");
    }

    @PostMapping("")
    public ResponseEntity<ChampionshipsTeams> create(@RequestHeader("Authorization") String authHeader, @RequestBody ChampionshipsTeamsDTO championshipsTeamsDTO) {
        ChampionshipsTeams championshipsTeamsSaved = championshipsTeamsCommandHandller.create(authHeader, championshipsTeamsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(championshipsTeamsSaved);
    }

//    @DeleteMapping("{ChampionshipsId}")
//    public ResponseEntity<String> delete( @RequestBody ChampionshipsTeamsDTO championshipsTeamsDTO) {
//        return ResponseEntity.ok("sucesso");
//    }
}
