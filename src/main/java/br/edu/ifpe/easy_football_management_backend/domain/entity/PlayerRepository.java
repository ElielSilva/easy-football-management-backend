package br.edu.ifpe.easy_football_management_backend.domain.entity;

import br.edu.ifpe.easy_football_management_backend.domain.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
}
