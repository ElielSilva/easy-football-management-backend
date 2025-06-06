package br.edu.ifpe.easy_football_management_backend.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChampionshipsRepository extends JpaRepository<Championships, UUID> {
    @Query("SELECT t FROM Championships c JOIN ChampionshipsTeams ct ON c.id = ct.championships.id JOIN Team t ON t.id = ct.team.id WHERE ct.championships = :championship")
    List<Team> findByChampionshipsContaining(Championships championship);

    List<Championships> findAllByUserId(UUID uuid);
}
