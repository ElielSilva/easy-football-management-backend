package br.edu.ifpe.easy_football_management_backend.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChampionshipsRepository extends JpaRepository<Championships, UUID> {
    @Query("SELECT t FROM Championships c JOIN ChampionshipsTeams ct ON c.id = ct.championship.id JOIN Team t ON t.id = ct.team.id WHERE ct.championship = :championship")
    List<Team> findByChampionshipsContaining(Championships championship);

    @Query("SELECT c FROM Championships c JOIN c.registeredTeams ct WHERE ct.team = :team")
    List<Championships> findChampionshipsRegisteredByTeam(@Param("team") Team team);

    @Query("SELECT c FROM Championships c JOIN ChampionshipsTeams ct ON c.id = ct.championship.id WHERE ct.team = :team")
    List<Championships> findChampionshipsByTeam(@Param("team") Team team);

    List<Championships> findAllByUserId(UUID uuid);

    @Query("SELECT u FROM Championships u " +
            "WHERE 1 = 1" +
            "AND (:name is null OR UPPER(u.name) LIKE UPPER(CONCAT('%', :name, '%')) )" +
            "AND (:status IS NULL OR u.status = :status)")
    List<Championships> findByNameLikeAndOptionalStatus(@Param("name") String name, @Param("status") StatusChampionship status);
}
