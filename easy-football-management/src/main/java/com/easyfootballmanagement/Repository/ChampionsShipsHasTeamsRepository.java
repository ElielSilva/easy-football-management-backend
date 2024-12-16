package com.easyfootballmanagement.Repository;

import com.easyfootballmanagement.Models.ChampionsShipsHasTeams;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Hidden
@Repository
public interface ChampionsShipsHasTeamsRepository extends JpaRepository<ChampionsShipsHasTeams, Long> {
}
