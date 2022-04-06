package com.toptal.soccer.utils;

import com.toptal.soccer.constants.PlayerType;
import com.toptal.soccer.data.model.Player;
import com.toptal.soccer.data.model.Team;
import com.toptal.soccer.exception.HttpException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class PlayerUtils {
  private static final int PLAYER_MIN_AGE = 18;
  private static final int PLAYER_MAX_AGE = 40;

  public static LocalDate getRandomDateOfBirth() {
    LocalDate minAge = LocalDate.now().minusYears(PLAYER_MIN_AGE);
    LocalDate maxAge = LocalDate.now().minusYears(PLAYER_MAX_AGE);
    long random = ThreadLocalRandom.current().nextLong(maxAge.toEpochDay(), minAge.toEpochDay());
    return LocalDate.ofEpochDay(random);
  }

  public static void validateIsPlayerOwnerAction(Player player) {
    if (player.getTeam().getId().intValue() != AuthUtils.getTeamIdFromJwt().intValue()) {
      throw new HttpException("Only Player owner is permitted to update", HttpStatus.BAD_REQUEST);
    }
  }

  public static void assignInitialPlayers(Team team) {
    List<Player> players = new ArrayList<>();
    for (PlayerType type : PlayerType.values()) {
      players.addAll(createPlayersByType(team, type));
    }
    setRelationShip(team, players);
  }

  private static List<Player> createPlayersByType(Team team, PlayerType type) {
    List<Player> players = new ArrayList<>();
    for (int i = 0; i < type.getInitialCount(); i++) {
      Player player = createPlayer(team.getUser().getUsername(), type, i + 1);
      players.add(player);
    }
    return players;
  }

  private static void setRelationShip(Team team, List<Player> players) {
    team.setPlayers(players);
    for (Player player : players) {
      player.setTeam(team);
    }
  }

  private static Player createPlayer(String userName, PlayerType type, int playerCnt) {
    Player player = Player.createNewPlayer();
    player.setType(type);
    player.setFirstName(String.format("%s %s", userName.toUpperCase(), type.name()));
    player.setLastName(String.format("No %s", playerCnt));
    return player;
  }

  public static long getRandomNewValue(Long value) {
    double increasedValInPercent = CommonUtils.getRandomDoubleInRange(10, 100) / 100.0;
    return (long) (value * (1 + increasedValInPercent));
  }
}
