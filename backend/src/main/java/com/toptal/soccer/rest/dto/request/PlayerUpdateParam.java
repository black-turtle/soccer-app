package com.toptal.soccer.rest.dto.request;

import com.toptal.soccer.constants.Country;
import com.toptal.soccer.data.model.Player;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.logging.log4j.util.Strings;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerUpdateParam {
  @ApiModelProperty @NonNull private Integer id;

  @ApiModelProperty(position = 1)
  private String firstName;

  @ApiModelProperty(position = 2)
  private String lastName;

  @ApiModelProperty(position = 3)
  private String country;

  public void updatePlayer(Player player) {
    if (Strings.isNotBlank(firstName)) {
      player.setFirstName(firstName);
    }

    if (Strings.isNotBlank(lastName)) {
      player.setLastName(lastName);
    }

    if (Strings.isNotBlank(country)) {
      player.setCountry(Country.getCountryByValue(country));
    }
  }
}
