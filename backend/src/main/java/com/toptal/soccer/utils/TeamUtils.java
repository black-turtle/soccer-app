package com.toptal.soccer.utils;

import com.toptal.soccer.data.model.Team;
import com.toptal.soccer.data.model.Transfer;
import com.toptal.soccer.data.model.User;
import com.toptal.soccer.exception.HttpException;
import org.springframework.http.HttpStatus;

public abstract class TeamUtils {
  public static void assignNewTeam(User user) {
    Team team = Team.createNewTeam();
    user.setTeam(team);
    team.setUser(user);
  }

  public static void validateTeamHasEnoughBudget(Team team, Transfer transfer) {
    if (transfer.getPrice() > team.getBudget()) {
      throw new HttpException(
          "Your team don't have enough budget to buy the player", HttpStatus.BAD_REQUEST);
    }
  }

  public static void validateBuyerTeam(Transfer transfer, Team newTeam) {
    if (transfer.getPlayer().getTeam().getId().intValue() == newTeam.getId().intValue()) {
      throw new HttpException("Your team already own this player", HttpStatus.BAD_REQUEST);
    }
  }
}
