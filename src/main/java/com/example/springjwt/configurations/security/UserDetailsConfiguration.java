package com.example.springjwt.configurations.security;

import com.example.springjwt.enums.UserRole;
import com.example.springjwt.models.User;
import com.example.springjwt.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class UserDetailsConfiguration implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserDetailsConfiguration(@NotNull UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(@NotNull String login) throws UsernameNotFoundException {
    final User user = userRepository
            .findByUsername(login)
            .orElseThrow(() -> new UsernameNotFoundException(login));

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), getAuthorities(user.getUserRole().getRole())
    );
  }

  @NotNull
  private Set<SimpleGrantedAuthority> getAuthorities(@NotNull UserRole role) {
    return Set.of(new SimpleGrantedAuthority(role.name()));
  }
}
