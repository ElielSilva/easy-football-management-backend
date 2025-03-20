package br.edu.ifpe.easy_football_management_backend.domain.Entity;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
}
