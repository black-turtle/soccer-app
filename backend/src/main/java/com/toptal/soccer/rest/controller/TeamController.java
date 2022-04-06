package com.toptal.soccer.rest.controller;

import com.toptal.soccer.constants.ApiEndPoints;
import com.toptal.soccer.data.model.Player;
import com.toptal.soccer.data.model.Team;
import com.toptal.soccer.rest.dto.request.TeamUpdateParam;
import com.toptal.soccer.rest.dto.response.TeamInfo;
import com.toptal.soccer.service.PlayerService;
import com.toptal.soccer.service.TeamService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndPoints.TEAMS_BASE)
@Api(tags = "teams", description = "Api for team management")
@RequiredArgsConstructor
public class TeamController {
  private final TeamService teamService;
  private final PlayerService playerService;

  @GetMapping
  @ApiOperation(
      value =
          "Get Team information owned by current user (user information is retrieved from JWT token)",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Something went wrong"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server Error")
      })
  public TeamInfo getTeam() {
    Team team = teamService.getCurrentTeam();

    return getTeamInfo(team);
  }

  private TeamInfo getTeamInfo(Team team) {
    List<Player> players = playerService.getPlayersByTeamId();

    return TeamInfo.createTeamInfo(team, players);
  }

  @PutMapping
  @ApiOperation(
      value =
          "Update Team information owned by current user (user information is retrieved from JWT token)",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Something went wrong"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server Error")
      })
  public TeamInfo updateTeam(
      @ApiParam("Team info update") @RequestBody TeamUpdateParam teamUpdateParam) {
    Team team = teamService.getCurrentTeam();

    teamUpdateParam.updateTeam(team);

    teamService.saveOrUpdate(team);

    return getTeamInfo(team);
  }
}
