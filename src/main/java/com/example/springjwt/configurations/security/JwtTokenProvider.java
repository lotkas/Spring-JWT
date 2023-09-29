package com.example.springjwt.configurations.security;

import com.example.springjwt.enums.UserRole;
import io.jsonwebtoken.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider {

  @Value(value = "${security.jwt-secret}")
  private String jwtSecret;

  @Value(value = "${security.jwt-validity-time-ms}")
  private Long jwtValidityTimeMs;

  private final UserDetailsConfiguration userDetails;
  private final JwtParser jwtParser;

  @Autowired
  public JwtTokenProvider(@NotNull UserDetailsConfiguration userDetails) {
    this.userDetails = userDetails;
    this.jwtParser = Jwts.parser();
  }

  @PostConstruct
  public void initSecret() {
    this.jwtParser.setSigningKey(jwtSecret);
  }

  public String createToken(String login, UserRole role) {
    Claims claims = Jwts.claims().setSubject(login);
    claims.put("role", role);
    Date now = new Date();
    Date validity = new Date(now.getTime() + jwtValidityTimeMs * 1000);

    return Jwts.builder()
      .setClaims(claims)
      .setIssuedAt(now)
      .setExpiration(validity)
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
      return !claimsJws.getBody().getExpiration().before(new Date());
    } catch (JwtException | IllegalArgumentException exception) {
      throw new JwtAuthenticationException();
    }
  }

  public Authentication getAuthentication(String token) {
    org.springframework.security.core.userdetails.UserDetails userDetails = this.userDetails.loadUserByUsername(getClientName(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getClientName(String token) {
    return jwtParser.parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest request) {
    final String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer")) {
      return null;
    }

    return getBearerToken(authHeader);
  }

  @NotNull
  private String getBearerToken(@NotNull String authHeader) {
    return authHeader.replace("Bearer", "").trim();
  }
}
