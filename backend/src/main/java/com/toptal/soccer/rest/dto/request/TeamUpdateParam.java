package com.toptal.soccer.rest.dto.request;

import com.toptal.soccer.constants.Country;
import com.toptal.soccer.data.model.Team;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamUpdateParam {
  @ApiModelProperty private String name;

  @ApiModelProperty(position = 1)
  private String country;

  public void updateTeam(Team team) {
    if (Strings.isNotBlank(name)) {
      team.setName(name);
    }

    if (Strings.isNotBlank(country)) {
      team.setCountry(Country.getCountryByValue(country));
    }
  }
}
