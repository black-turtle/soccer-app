package com.toptal.soccer.rest.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferRequestParam {
  @ApiModelProperty private int playerId;

  @ApiModelProperty(position = 1)
  @PositiveOrZero
  private long price;
}
