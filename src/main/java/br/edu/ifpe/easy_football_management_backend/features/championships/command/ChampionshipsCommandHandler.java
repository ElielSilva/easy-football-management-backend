package br.edu.ifpe.easy_football_management_backend.features.championships.command;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Championships;
import br.edu.ifpe.easy_football_management_backend.domain.entity.ChampionshipsRepository;
import br.edu.ifpe.easy_football_management_backend.features.championships.ChampionshipsDTO;
import br.edu.ifpe.easy_football_management_backend.features.championships.ChampionshipsMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class ChampionshipsCommandHandler {

    private final ChampionshipsRepository repository;
    private final ChampionshipsMapper mapper;

    public ChampionshipsCommandHandler(ChampionshipsRepository championshipsRepository, ChampionshipsMapper mapper) {
        this.repository = championshipsRepository;
        this.mapper = mapper;
    }

    @Transactional
    public Championships handler(ChampionshipsDTO championshipsDTO) {
        var entity = mapper.toEntity(championshipsDTO);
        entity.setStartDate(LocalDate.now());
        return repository.save(entity);
    }

    @Transactional
    public Championships handler(ChampionshipsDTO championshipsDTO, UUID championshipsUUID) {
        if (championshipsDTO != null) {
            if (isNotExist(championshipsUUID)) {
                throw new IllegalArgumentException("championships ID " + championshipsUUID + " does not exist");
            }
        }
        var entity = mapper.toEntity(championshipsDTO);
        entity.setId(championshipsUUID);
        return repository.save(entity);
    }

    @Transactional
    public void handler(UUID championshipsUUID) {
        if (championshipsUUID != null) {
            if (isNotExist(championshipsUUID)) {
                throw new IllegalArgumentException("championships ID " + championshipsUUID + " does not exist");
            }
            repository.deleteById(championshipsUUID);
        }
    }

    private boolean isNotExist(UUID teamId) {
        return repository.existsById(teamId);
    }

}
