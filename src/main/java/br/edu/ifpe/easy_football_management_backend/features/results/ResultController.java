package br.edu.ifpe.easy_football_management_backend.features.results;


import br.edu.ifpe.easy_football_management_backend.domain.entity.Match;
import br.edu.ifpe.easy_football_management_backend.features.results.commands.ResultCommandHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/results")
public class ResultController {

    private final ResultCommandHandler resultCommandHandler;

    public ResultController(ResultCommandHandler resultCommandHandler) {
        this.resultCommandHandler = resultCommandHandler;
    }

    @PutMapping("/")
    public ResponseEntity<Match> updateResult(@RequestBody @Valid ResultDTO resultDTO) {
        Match match = resultCommandHandler.recordMatchResult(resultDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(match);
    }

    @GetMapping("/")
    public ResponseEntity<ResultDTO> getResult() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResultDTO());
    }

}