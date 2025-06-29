package br.edu.ifpe.easy_football_management_backend.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
    @Query("SELECT p FROM Player p WHERE p.team.id = :teamId")
    List<Player> getAllPlayersForTeamId(@Param("teamId") UUID teamId);

    List<Player> findByTeamId(UUID teamId);
}
