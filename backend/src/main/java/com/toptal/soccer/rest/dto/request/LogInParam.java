package com.toptal.soccer.rest.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInParam {
  @ApiModelProperty private String username;

  @ApiModelProperty(position = 1)
  private String password;
}
