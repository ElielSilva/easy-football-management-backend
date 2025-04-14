package br.edu.ifpe.easy_football_management_backend.features.auth;

import br.edu.ifpe.easy_football_management_backend.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String Email);

    UUID id(UUID id);
}
