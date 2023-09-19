package com.example.SpringJWT.services;

import com.example.SpringJWT.configurations.ApiException;
import com.example.SpringJWT.dto.AuthRequestDTO;
import com.example.SpringJWT.enums.UserRole;
import com.example.SpringJWT.models.Role;
import com.example.SpringJWT.models.User;
import com.example.SpringJWT.repositories.RoleRepository;
import com.example.SpringJWT.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(@NotNull UserRepository userRepository,
                       @NotNull RoleRepository roleRepository,
                       @NotNull PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getByLogin(@NotNull String login) {
        logger.info("getByLogin() start");
        return userRepository.findByUsername(login).orElse(null);
    }

    public Long findEmployeeIdByUsername(@NotNull String username) {
        logger.info("findEmployeeIdByUsername() start");
        Long employeeId = userRepository.findEmployeeIdByUsername(username);
        if (employeeId == null) {
            throw new RuntimeException("EmployeeId not found for username: " + username);
        }
        return employeeId;
    }

    public User updateUserEmployeeId(Long userId, Long employeeId) {
        logger.info("updateUserEmployeeId() start");
        User user = userRepository.getById(userId);
        user.setEmployeeId(employeeId);

        return userRepository.save(user);
    }

    @Transactional
    public void createNewUser(@NotNull AuthRequestDTO request) {
        logger.info("createNewUser() start");
        if (getByLogin(request.getLogin()) != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        final Role userRole = roleRepository.findByRole(UserRole.USER)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST));

        final User user = new User();
        user.setUsername(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserRole(userRole);
        userRepository.save(user);
        logger.info("createNewUser() end");
    }
}
