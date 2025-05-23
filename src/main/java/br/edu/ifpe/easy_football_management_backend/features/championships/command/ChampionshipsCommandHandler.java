package br.edu.ifpe.easy_football_management_backend.features.championships.command;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Championships;
import br.edu.ifpe.easy_football_management_backend.domain.entity.ChampionshipsRepository;
import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import br.edu.ifpe.easy_football_management_backend.features.championships.ChampionshipsDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class ChampionshipsCommandHandler {

    private final ChampionshipsRepository repository;

    public ChampionshipsCommandHandler(ChampionshipsRepository championshipsRepository) {
        this.repository = championshipsRepository;
    }

    @Transactional
    public Championships handler(ChampionshipsDTO championshipsDTO) {
        System.out.println(championshipsDTO);
        var entity = this.map(championshipsDTO);
        return repository.save(entity);
    }

    @Transactional
    public Championships handler(ChampionshipsDTO championshipsDTO, UUID championshipsUUID) {
        if (championshipsDTO != null) {
            if (!isExist(championshipsUUID)){
                throw new IllegalArgumentException("championships ID " + championshipsUUID + " does not exist");
            }
        }
        var entity = this.map(championshipsDTO);
        entity.setId(championshipsUUID);
        return repository.save(entity);
    }

    @Transactional
    public void handler(UUID championshipsUUID) {
        if (championshipsUUID != null) {
            if (!isExist(championshipsUUID)){
                throw new IllegalArgumentException("championships ID " + championshipsUUID + " does not exist");
            }
            repository.deleteById(championshipsUUID);
        }
    }

    private boolean isExist(UUID teamId) {
        return repository.existsById(teamId);
    }


    private Championships map(ChampionshipsDTO championshipsDTO) {
        var result = new Championships();
        result.setName(championshipsDTO.name());
        result.setQuantityTeams(championshipsDTO.quantityTeams());
        result.setType(championshipsDTO.typeChampionship());
        result.setStatus(championshipsDTO.statusChampionship());
        result.setDescription(championshipsDTO.description());
        result.setAward(championshipsDTO.award());
        result.setCreatedAt(new Date());
        if (championshipsDTO.userID() != null) {
            result.setUser(User.builder().id(championshipsDTO.userID()).build());
        }
        return result;
    }

    private void isValid(ChampionshipsDTO championshipsDTO) {
        if (championshipsDTO.name() == null || championshipsDTO.name().isEmpty() || championshipsDTO.name().length() < 3 || championshipsDTO.name().length() > 100) {
            throw new IllegalArgumentException();
        }
        if (championshipsDTO.quantityTeams() == 0 || championshipsDTO.quantityTeams() < 2 || championshipsDTO.quantityTeams() > 32) {
            throw new IllegalArgumentException();
        }
        if (championshipsDTO.typeChampionship() == null) {
            throw new IllegalArgumentException();
        }
    }
}
