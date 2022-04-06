package com.toptal.soccer.rest.controller;

import com.toptal.soccer.constants.ApiEndPoints;
import com.toptal.soccer.service.UtilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(ApiEndPoints.UTILS_BASE)
@Api(tags = "utils", description = "Api for utils management")
@RequiredArgsConstructor
public class UtilController {
  private final UtilService utilService;

  @GetMapping("/countries")
  @ApiOperation(value = "Get all available countries list")
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Something went wrong"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server Error")
      })
  public Set<String> getCounties() {
    return utilService.getAllCountyNames();
  }
}
