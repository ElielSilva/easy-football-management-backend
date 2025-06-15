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
    @Query("DELETE FROM ChampionshipsTeams ct WHERE ct.team.id = :teamId AND ct.championship.id = :ChampionshipsId")
    void deleteByTeamIdAndChampionshipsId(UUID teamId, UUID ChampionshipsId);

    @Query("SELECT CASE WHEN COUNT(ct) > 0 THEN true ELSE false END FROM ChampionshipsTeams ct WHERE ct.team.id = :teamId AND ct.championship.id = :ChampionshipsId")
    boolean existsTeam(UUID teamId, UUID ChampionshipsId);

    Optional<ChampionshipsTeams> findByChampionship_IdAndTeam_Id(UUID championshipsId, UUID teamId);

    @Query("SELECT count(t) FROM Championships c JOIN ChampionshipsTeams ct ON c.id = ct.championship.id JOIN Team t ON t.id = ct.team.id WHERE ct.championship.id = :championshipId")
    int countByTeamContains(UUID championshipId);


    List<ChampionshipsTeams> findByChampionshipId(UUID championshipId);
}
