package br.edu.ifpe.easy_football_management_backend.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StandingRepository extends JpaRepository<Standing, UUID> {
    Standing findByChampionshipAndTeam(Championships championship, Team team);

    List<Standing> findByChampionshipIdOrderByPointsDescGoalsForDesc(UUID championshipId);
    Optional<Standing> findByChampionshipIdAndTeamId(UUID championshipId, UUID teamId);
}
