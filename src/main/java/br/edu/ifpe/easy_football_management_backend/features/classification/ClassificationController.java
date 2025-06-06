package br.edu.ifpe.easy_football_management_backend.features.classification;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Matchs;
import br.edu.ifpe.easy_football_management_backend.domain.entity.Result;
import br.edu.ifpe.easy_football_management_backend.features.classification.query.ClassificationQueryHandler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classification")
@SecurityRequirement(name = "bearerAuth")
public class ClassificationController {

    private final ClassificationQueryHandler classificationQueryHandler;

    public ClassificationController(ClassificationQueryHandler classificationQueryHandler) {
        this.classificationQueryHandler = classificationQueryHandler;
    }

    @GetMapping("/matchs/{Championships}")
    public ResponseEntity<List<Matchs>> FindByIdMatchs(@RequestParam("Championships") @NotNull @Valid UUID tourmanetId) {
        return ResponseEntity.ok(classificationQueryHandler.handlerMatches(tourmanetId));
    }

    @GetMapping("/{Championships}")
    public ResponseEntity<List<Result>> FindByIdClassification(@RequestParam("Championships") @NotNull @Valid UUID championshipId) {
        return ResponseEntity.ok(classificationQueryHandler.handler(championshipId));
    }
}
