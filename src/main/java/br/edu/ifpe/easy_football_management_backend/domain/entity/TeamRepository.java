package br.edu.ifpe.easy_football_management_backend.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
    @Query("SELECT t.id FROM Team t WHERE t.user.id = :userId")
    Optional<UUID> findFirstTeamIdByUserId(@Param("userId") UUID userId);
}
