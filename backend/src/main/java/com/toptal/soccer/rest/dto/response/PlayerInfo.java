package com.toptal.soccer.rest.dto.response;

import com.toptal.soccer.data.model.Player;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Data
@Builder
public class PlayerInfo {
  private int playerId;
  private String firstName;
  private String lastName;
  private String country;
  private long age;
  private long value;
  private String type;
  private boolean canTransfer;

  public static PlayerInfo createPlayerInfo(Player player) {
    return PlayerInfo.builder()
        .playerId(player.getId())
        .firstName(player.getFirstName())
        .lastName(player.getLastName())
        .country(player.getCountry().getValue())
        .age(Math.abs(ChronoUnit.YEARS.between(LocalDate.now(), player.getDateOfBirth())))
        .value(player.getValue())
        .type(player.getType().name())
        .canTransfer(Objects.nonNull(player.getTransfer()))
        .build();
  }
}
