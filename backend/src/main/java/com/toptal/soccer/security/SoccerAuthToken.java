package com.toptal.soccer.security;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.HashSet;

@Getter
public class SoccerAuthToken extends UsernamePasswordAuthenticationToken {
  private final Claims claims;

  public SoccerAuthToken(String userName, Claims claims) {
    super(userName, "", new HashSet<>());
    this.claims = claims;
  }
}
