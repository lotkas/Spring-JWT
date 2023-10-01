package com.example.springjwt.repositories;

import com.example.springjwt.models.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "userRole")
    Optional<User> findByUsername(@NotNull String login);

    @Query("SELECT u.employeeId FROM User u WHERE u.username = :username")
    Long findEmployeeIdByUsername(@NotNull @Param("username") String username);
}
