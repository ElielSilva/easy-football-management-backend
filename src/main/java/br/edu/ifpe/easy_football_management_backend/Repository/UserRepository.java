package br.edu.ifpe.easy_football_management_backend.Repository;

import br.edu.ifpe.easy_football_management_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
