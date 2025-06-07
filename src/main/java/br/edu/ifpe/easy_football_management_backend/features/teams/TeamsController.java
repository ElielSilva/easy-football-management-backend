package br.edu.ifpe.easy_football_management_backend.features.teams;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;
import br.edu.ifpe.easy_football_management_backend.features.teams.command.TeamCommandHandler;
import br.edu.ifpe.easy_football_management_backend.features.teams.query.TeamQueryHandler;
import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/teams")
@SecurityRequirement(name = "bearerAuth")
public class TeamsController {

    private final TeamCommandHandler teamCommandHandler;
    private final TeamQueryHandler teamQueryHandler;


    public TeamsController(TeamCommandHandler teamCommandHandler, TeamQueryHandler teamQueryHandler) {
        this.teamCommandHandler = teamCommandHandler;
        this.teamQueryHandler = teamQueryHandler;
    }

    @GetMapping("")
    public ResponseEntity<List<TeamDtoResponse>> getAll() {
        var result = teamQueryHandler.handler();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Optional<TeamDtoResponse>> get(@PathVariable UUID teamId) {
        var result = teamQueryHandler.handler(teamId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<Team> update(@PathVariable UUID teamId, @RequestBody TeamDTO team, @RequestHeader("Authorization") String token) {
        var payload = new TeamDTO(team.name(), team.img(), UUID.fromString(TokenService.extract(token)));
        return ResponseEntity.ok(teamCommandHandler.handler(payload, teamId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID teamId) {
        teamCommandHandler.handler(teamId);
        return ResponseEntity.ok("sucesso");
    }

    @PostMapping
    public ResponseEntity<Team> create(@RequestBody TeamDTO team, @RequestHeader("Authorization") String token) {
        System.out.println(token);
        var id = TokenService.extract(token);
        var payload = new TeamDTO(team.name(), team.img(), UUID.fromString(id));
        var r = teamCommandHandler.handler(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }
}
