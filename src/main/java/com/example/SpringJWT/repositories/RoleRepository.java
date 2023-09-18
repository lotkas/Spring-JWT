package com.example.SpringJWT.repositories;

import com.example.SpringJWT.enums.UserRole;
import com.example.SpringJWT.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(UserRole roleName);
}
