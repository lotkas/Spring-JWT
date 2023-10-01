package com.example.springjwt.repositories;

import com.example.springjwt.enums.UserRole;
import com.example.springjwt.models.Role;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(@NotNull UserRole roleName);
}
