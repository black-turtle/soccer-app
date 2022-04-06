package com.toptal.soccer.security;

import com.toptal.soccer.data.model.User;
import com.toptal.soccer.data.repository.UserRepository;
import com.toptal.soccer.exception.HttpException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new HttpException(
          String.format("User '%s' not found", username), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    return org.springframework.security.core.userdetails.User.withUsername(username)
        .password(user.getPassword())
        .authorities(new HashSet<>())
        .build();
  }
}
