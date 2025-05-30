package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChampionshipsTeamsRepository extends JpaRepository<ChampionshipsTeams, UUID> {
//    Optional<ChampionshipsTeamsRepository> findById(UUID id);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("DELETE FROM ChampionshipsTeams ct WHERE ct.team.id = :teamId AND ct.championships.id = :ChampionshipsId")
    void deleteByTeamIdAndChampionshipsId(UUID teamId, UUID ChampionshipsId);
    boolean existsByTeamId(UUID teamId);
    Optional<ChampionshipsTeams> findByChampionships_IdAndTeam_Id(UUID championshipsId, UUID teamId);

    @Query("SELECT count(t) FROM Championships c JOIN ChampionshipsTeams ct ON c.id = ct.championships.id JOIN Team t ON t.id = ct.team.id WHERE ct.championships.id = :championshipId")
    int countByTeamContains(UUID championshipId);

    @Query("SELECT 1 FROM Championships c JOIN ChampionshipsTeams ct ON c.id = ct.championships.id JOIN Team t ON t.id = ct.team.id WHERE ct.championships.id = :championshipId")
    boolean IsCompletedChampionship(UUID championshipId);
}
