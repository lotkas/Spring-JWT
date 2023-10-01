package com.example.springjwt.controllers;

import com.example.springjwt.configurations.security.JwtTokenProvider;
import com.example.springjwt.dto.authdto.AuthRequestDTO;
import com.example.springjwt.dto.authdto.AuthResponseDTO;
import com.example.springjwt.models.User;
import com.example.springjwt.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private static final String LOGIN = "/login";

    private static final String LOGOUT = "/logout";

    private static final String REGISTRATION = "/registration";

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(@NotNull AuthenticationManager authenticationManager,
                          @NotNull UserService userService,
                          @NotNull JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(LOGIN)
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO request) {
        logger.info("AuthController. authenticate() start");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        final User user = userService.getByLogin(request.getLogin());
        final String token = jwtTokenProvider.createToken(user.getUsername(), user.getUserRole().getRole());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping(LOGOUT)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        logger.info("AuthController. logout() start");
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        logger.info("AuthController. logout() end");
    }

    @PostMapping(REGISTRATION)
    public ResponseEntity<String> registration(@RequestBody AuthRequestDTO request) {
        logger.info("AuthController. registration() start");
        userService.createNewUser(request);

        return ResponseEntity.ok().build();
    }
}
