package com.toptal.soccer.utils;

import com.toptal.soccer.data.model.User;
import com.toptal.soccer.exception.HttpException;
import org.springframework.http.HttpStatus;

public abstract class UserUtils {
  public static void createTeamAndPlayers(User user) {
    try {
      TeamUtils.assignNewTeam(user);
      PlayerUtils.assignInitialPlayers(user.getTeam());
    } catch (Exception e) {
      e.printStackTrace();
      throw new HttpException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public static void validateUser(String userName) {
    String authenticatedUserName = AuthUtils.getUserNameFromJwtToken();
    if (!authenticatedUserName.equals(userName)) {
      throw new HttpException("User doesn't have permission", HttpStatus.BAD_REQUEST);
    }
  }
}
