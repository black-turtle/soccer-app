package com.toptal.soccer.utils;

import com.toptal.soccer.constants.JwtClaims;
import com.toptal.soccer.security.SoccerAuthToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public abstract class AuthUtils {
  public static String getUserNameFromJwtToken() {
    SoccerAuthToken authToken = getAuthToken();
    return authToken.getName();
  }

  private static SoccerAuthToken getAuthToken() {
    return (SoccerAuthToken) SecurityContextHolder.getContext().getAuthentication();
  }

  public static boolean isTokenPresentInContext() {
    SoccerAuthToken token = getAuthToken();
    return Objects.nonNull(token);
  }

  public static Integer getTeamIdFromJwt() {
    return Integer.valueOf(getValueFromJwtClaim(JwtClaims.TID.getValue()));
  }

  private static String getValueFromJwtClaim(String claimName) {
    SoccerAuthToken authToken = getAuthToken();
    return String.valueOf(authToken.getClaims().get(claimName));
  }

  public static Integer getUserIdFromJwt() {
    return Integer.valueOf(getValueFromJwtClaim(JwtClaims.UID.getValue()));
  }
}
