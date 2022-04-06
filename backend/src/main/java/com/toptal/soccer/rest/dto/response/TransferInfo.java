package com.toptal.soccer.rest.dto.response;

import com.toptal.soccer.data.model.Player;
import com.toptal.soccer.data.model.Transfer;
import com.toptal.soccer.data.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@Builder
public class TransferInfo {
  private int transferId;
  private String firstName;
  private String lastName;
  private String country;
  private long age;
  private long value;
  private String type;
  private long price;
  private String owner;

  public static TransferInfo createTransferInfo(Transfer transfer) {
    Player player = transfer.getPlayer();
    User user = player.getTeam().getUser();
    return TransferInfo.builder()
        .transferId(transfer.getId())
        .firstName(player.getFirstName())
        .lastName(player.getLastName())
        .country(player.getCountry().getValue())
        .age(Math.abs(ChronoUnit.YEARS.between(LocalDate.now(), player.getDateOfBirth())))
        .value(player.getValue())
        .type(player.getType().name())
        .price(transfer.getPrice())
        .owner(user.getUsername())
        .build();
  }
}
