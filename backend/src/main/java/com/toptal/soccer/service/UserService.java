package com.toptal.soccer.service;

import com.toptal.soccer.data.model.User;
import com.toptal.soccer.data.repository.TeamRepository;
import com.toptal.soccer.data.repository.UserRepository;
import com.toptal.soccer.exception.HttpException;
import com.toptal.soccer.security.JwtTokenProvider;
import com.toptal.soccer.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final TeamRepository teamRepository;

  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;

  public String login(String username, String password) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
      int userId = userRepository.findByUsername(username).getId();
      int teamId = teamRepository.findByUserId(userId).getId();
      return jwtTokenProvider.createToken(username, userId, teamId);

    } catch (AuthenticationException e) {
      throw new HttpException(
          "Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(User user) {
    if (userRepository.existsByUsername(user.getUsername())
        || userRepository.existsByEmail(user.getEmail())) {
      throw new HttpException("Username/Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    } else {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      UserUtils.createTeamAndPlayers(user);
      userRepository.save(user);
      int userId = user.getId();
      int teamId = teamRepository.findByUserId(userId).getId();
      return jwtTokenProvider.createToken(user.getUsername(), userId, teamId);
    }
  }

  public String delete(String username) {
    UserUtils.validateUser(username);
    if (userRepository.existsByUsername(username)) {
      userRepository.deleteByUsername(username);
      return username;
    } else {
      throw new HttpException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
  }

  public List<User> getUsers(int page, int limit) {
    return userRepository.findAll(PageRequest.of(page, limit)).get().collect(Collectors.toList());
  }
}
