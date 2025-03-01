package com.easyfootballmanagement.infrastructure.repository;

import com.easyfootballmanagement.domain.entities.Users;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Hidden
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
