package br.edu.ifpe.easy_football_management_backend.features.classification.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Result;
import br.edu.ifpe.easy_football_management_backend.domain.entity.ResultRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ClassificationQueryHandler {

    private final ResultRepository resultRepository;

    public ClassificationQueryHandler(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public Optional<Result> handler(UUID id) {
        return resultRepository.findById(id);
    }
}
