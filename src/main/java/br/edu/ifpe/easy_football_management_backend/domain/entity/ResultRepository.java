package br.edu.ifpe.easy_football_management_backend.domain.entity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ResultRepository extends JpaRepository<Result, UUID> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("DELETE FROM Result ct WHERE ct.championship.id = :ChampionshipsId")
    void deleteByChampionshipsId(UUID ChampionshipsId);

    @Query("select c from Result c where c.championship.id = :championshipId")
    List<Result> findByChampionshipId(UUID championshipId);

}
