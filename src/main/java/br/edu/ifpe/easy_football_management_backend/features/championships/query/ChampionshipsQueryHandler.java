package br.edu.ifpe.easy_football_management_backend.features.championships.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Championships;
import br.edu.ifpe.easy_football_management_backend.domain.entity.ChampionshipsRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ChampionshipsQueryHandler {

    private final ChampionshipsRepository repository;

    public ChampionshipsQueryHandler(ChampionshipsRepository repository) {
        this.repository = repository;
    }

    public List<Championships> handler() {
        return repository.findAll();
    }

    public Optional<Championships> handler(UUID id) {
        return repository.findById(id);
    }
}
