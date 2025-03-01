package com.easyfootballmanagement.infrastructure.repository;

import com.easyfootballmanagement.domain.entities.ChampionsShips;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Hidden
@Repository
public interface ChampionsShipRepository extends JpaRepository<ChampionsShips, Long> {
}
