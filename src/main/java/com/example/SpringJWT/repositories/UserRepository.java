package com.example.SpringJWT.repositories;

import com.example.SpringJWT.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "userRole")
    Optional<User> findByUsername(String login);
}
