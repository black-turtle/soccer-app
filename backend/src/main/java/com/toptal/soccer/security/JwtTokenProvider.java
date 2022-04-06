package com.toptal.soccer.security;

import com.toptal.soccer.constants.JwtClaims;
import com.toptal.soccer.exception.HttpException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
  private final AuthUserDetailsService userDetailsService;

  @Value("${security.jwt.token.validity}")
  private long validityInMilliseconds;

  @Value("${security.jwt.token.secretKey}")
  private String secretKey;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(String username, Integer userId, Integer teamId) {

    Claims claims = Jwts.claims().setSubject(username);
    claims.put(JwtClaims.UID.getValue(), userId);
    claims.put(JwtClaims.TID.getValue(), teamId);

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public SoccerAuthToken getAuthentication(String token) {
    String username = getUsername(token);
    return new SoccerAuthToken(username, getAllClaims(token));
  }

  public String getUsername(String token) {
    return getAllClaims(token).getSubject();
  }

  public Claims getAllClaims(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      getAllClaims(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      throw new HttpException("Expired or invalid JWT token", HttpStatus.FORBIDDEN);
    }
  }
}
