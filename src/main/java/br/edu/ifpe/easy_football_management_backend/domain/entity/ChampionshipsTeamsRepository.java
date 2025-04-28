package br.edu.ifpe.easy_football_management_backend.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChampionshipsTeamsRepository extends JpaRepository<ChampionshipsTeams, UUID> {
//    Optional<ChampionshipsTeamsRepository> findById(UUID id);
    void deleteByTeamIdAndChampionshipsId(UUID teamId, UUID ChampionshipsId);
    boolean existsByTeamId(UUID teamId);
}
