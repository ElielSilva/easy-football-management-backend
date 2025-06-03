package br.edu.ifpe.easy_football_management_backend.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StandingRepository extends JpaRepository<Standing, UUID> {
    Standing findByChampionshipAndTeam(Championships championship, Team team);
}
