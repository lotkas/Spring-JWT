package com.example.SpringJWT.configurations.security;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

  public JwtAuthenticationException() {
    super("JWT token is expired on invalid");
  }
}
