package br.edu.ifpe.easy_football_management_backend.features.classification;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Result;
import br.edu.ifpe.easy_football_management_backend.features.classification.query.ClassificationQueryHandler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/classification")
@SecurityRequirement(name = "bearerAuth")
public class ClassificationController {

    private final ClassificationQueryHandler classificationQueryHandler;

    public ClassificationController(ClassificationQueryHandler classificationQueryHandler) {
        this.classificationQueryHandler = classificationQueryHandler;
    }

    @GetMapping("/{ChampionshipsTeamsId}")
    public ResponseEntity<Optional<Result>> FindById(@PathVariable("ChampionshipsTeamsId") @NotNull @NotEmpty @Valid UUID tourmanetId) {
        return ResponseEntity.ok(classificationQueryHandler.handler(tourmanetId));
    }
}
