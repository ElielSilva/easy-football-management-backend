package br.edu.ifpe.easy_football_management_backend.features.championships;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Championships;
import br.edu.ifpe.easy_football_management_backend.features.championships.command.ChampionshipsCommandHandler;
import br.edu.ifpe.easy_football_management_backend.features.championships.query.ChampionshipsQueryHandler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/championships")
@SecurityRequirement(name = "bearerAuth")
public class ChampionshipsController {

    private final ChampionshipsQueryHandler championshipsQueryHandler;
    private final ChampionshipsCommandHandler championshipsCommandHandler;

    public ChampionshipsController(ChampionshipsQueryHandler championshipsQueryHandler, ChampionshipsCommandHandler championshipsCommandHandler) {
        this.championshipsQueryHandler = championshipsQueryHandler;
        this.championshipsCommandHandler = championshipsCommandHandler;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Championships>> getAll() {
        var result = championshipsQueryHandler.handler();
        return ResponseEntity.ok(result);
    }

    @GetMapping("")
    public ResponseEntity<List<Championships>> get(@RequestHeader ("Authorization") String authorization) {
        var result = championshipsQueryHandler.handler(authorization);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{championshipId}")
    public ResponseEntity<Optional<Championships>> get(@PathVariable  UUID championshipId) {
        System.out.println("AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        System.out.println(championshipId);
        System.out.println("#####################################################");
        var result = championshipsQueryHandler.handler(championshipId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Championships> update(@RequestParam UUID championshipId, @RequestBody @Valid ChampionshipsDTO championshipsDTO) {
        return ResponseEntity.ok(championshipsCommandHandler.handler(championshipsDTO, championshipId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestParam UUID championshipId) {
        championshipsCommandHandler.handler(championshipId);
        return ResponseEntity.ok("sucesso");
    }

    @PostMapping
    public ResponseEntity<Championships> create(@RequestBody @Valid ChampionshipsDTO championshipsDTO) {
        var r = championshipsCommandHandler.handler(championshipsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }
}
