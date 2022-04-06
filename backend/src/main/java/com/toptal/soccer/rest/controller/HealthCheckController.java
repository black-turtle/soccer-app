package com.toptal.soccer.rest.controller;

import com.toptal.soccer.constants.ApiEndPoints;
import com.toptal.soccer.rest.dto.response.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndPoints.HEALTH_BASE)
@Api(tags = "health", description = "Api for checking server is on or off")
@RequiredArgsConstructor
public class HealthCheckController {
  @GetMapping()
  @ApiOperation(value = "check server is running or not")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
  public HttpResponse healthCheck() {
    return new HttpResponse("OK");
  }
}
