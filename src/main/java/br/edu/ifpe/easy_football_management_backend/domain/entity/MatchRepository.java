package br.edu.ifpe.easy_football_management_backend.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MatchRepository extends JpaRepository<Match, UUID> {
    List<Match> findByChampionshipId(UUID championshipId);

    List<Match> findByChampionshipIdAndStatus(UUID championshipId, MatchStatus status);

    @Query("SELECT m FROM Match m WHERE m.nextMatch = :nextMatch")
    List<Match> findByNextMatch(@Param("nextMatch") Match nextMatch);
}
