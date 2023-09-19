package com.example.SpringJWT.controllers;

import com.example.SpringJWT.configurations.security.JwtTokenProvider;
import com.example.SpringJWT.dto.AuthRequestDTO;
import com.example.SpringJWT.dto.AuthResponseDTO;
import com.example.SpringJWT.models.User;
import com.example.SpringJWT.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(@NotNull AuthenticationManager authenticationManager,
                          @NotNull UserService userService,
                          @NotNull JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO request) {
        logger.info("/login start");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        final User user = userService.getByLogin(request.getLogin());
        final String token = jwtTokenProvider.createToken(user.getUsername(), user.getUserRole().getRole());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        logger.info("/logout start");
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        logger.info("/logout complete");
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody AuthRequestDTO request) {
        logger.info("/registration start");
        userService.createNewUser(request);

        return ResponseEntity.ok().build();
    }
}
