package com.toptal.soccer.rest.dto.response;

import com.toptal.soccer.data.model.Player;
import com.toptal.soccer.data.model.Team;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeamInfo {
  private String name;

  private String country;

  private long budget;

  private long value;

  private int totalPlayers;

  public static TeamInfo createTeamInfo(Team team, List<Player> players) {
    long value = players.stream().mapToLong(Player::getValue).sum();
    return TeamInfo.builder()
        .name(team.getName())
        .country(team.getCountry().getValue())
        .budget(team.getBudget())
        .value(value)
        .totalPlayers(players.size())
        .build();
  }
}
