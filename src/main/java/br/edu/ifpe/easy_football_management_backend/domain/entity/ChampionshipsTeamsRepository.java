package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChampionshipsTeamsRepository extends JpaRepository<ChampionshipsTeams, UUID> {
//    Optional<ChampionshipsTeamsRepository> findById(UUID id);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("DELETE FROM ChampionshipsTeams ct WHERE ct.team.id = :teamId AND ct.championships.id = :ChampionshipsId")
    void deleteByTeamIdAndChampionshipsId(UUID teamId, UUID ChampionshipsId);
    boolean existsByTeamId(UUID teamId);
    Optional<ChampionshipsTeams> findByChampionships_IdAndTeam_Id(UUID championshipsId, UUID teamId);
}
