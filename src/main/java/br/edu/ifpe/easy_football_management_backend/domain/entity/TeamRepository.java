package br.edu.ifpe.easy_football_management_backend.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    @Query("SELECT t.id FROM Team t WHERE t.user.id = :userId")
    Optional<UUID> findFirstTeamIdByUserId(@Param("userId") UUID userId);

    @Query("SELECT t FROM Team t WHERE t.user.id = :userId")
    Optional<Team> findFirstClassTeamIdByUserId(@Param("userId") UUID userId);

    Team findByUser(User user);

    @Query("SELECT c FROM Championships c JOIN ChampionshipsTeams ct ON c.id = ct.championship.id WHERE ct.team = :team")
    List<Championships> findByTeamsContaining(Team team);

//    @Query("SELECT t FROM Team t WHERE t.user.id = :userId")
//    Optional<Team> findFirstTeamByUserId(@Param("userId") UUID userId);
}
