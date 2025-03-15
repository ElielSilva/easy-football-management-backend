package br.edu.ifpe.easy_football_management_backend.Infrestructure.Repository;

import br.edu.ifpe.easy_football_management_backend.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String Email);
}
