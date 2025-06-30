package br.edu.ifpe.easy_football_management_backend.features.championships.query;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Championships;
import br.edu.ifpe.easy_football_management_backend.domain.entity.ChampionshipsRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.StatusChampionship;
import br.edu.ifpe.easy_football_management_backend.infrestructure.security.TokenService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ChampionshipsQueryHandler {

    private final ChampionshipsRepository repository;
    private final TokenService tokenService;

    public ChampionshipsQueryHandler(ChampionshipsRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    public List<Championships> handler() {
        return repository.findAll();
    }

    public List<Championships> handler(String authHeader) {
        String userId = tokenService.extractID(authHeader);
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        return repository.findAllByUserId(UUID.fromString(userId));
    }

    public Optional<Championships> handler(UUID id) {
        // trazer getChamPionsChiupID ->>
        return repository.findById(id);
    }


    public List<Championships> filterByNameAndStatus(String name, StatusChampionship status) {
        return repository.findByNameLikeAndOptionalStatus(name, status);
    }
}
