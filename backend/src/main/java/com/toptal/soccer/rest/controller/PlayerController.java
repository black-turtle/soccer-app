package com.toptal.soccer.rest.controller;

import com.toptal.soccer.constants.ApiEndPoints;
import com.toptal.soccer.data.model.Player;
import com.toptal.soccer.rest.dto.request.PlayerUpdateParam;
import com.toptal.soccer.rest.dto.response.HttpResponse;
import com.toptal.soccer.rest.dto.response.PlayerInfo;
import com.toptal.soccer.service.PlayerService;
import com.toptal.soccer.utils.PlayerUtils;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiEndPoints.PLAYERS_BASE)
@Api(tags = "players", description = "Api for player management")
@RequiredArgsConstructor
public class PlayerController {
  private final PlayerService playerService;

  @GetMapping
  @ApiOperation(
      value =
          "Get All players information owned by current user (user information is retrieved from JWT token)",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Something went wrong"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server Error")
      })
  public List<PlayerInfo> getPlayers(
      @ApiParam("page") @RequestParam(required = false, defaultValue = "0") Integer page,
      @ApiParam("Limit") @RequestParam(required = false, defaultValue = "" + Integer.MAX_VALUE)
          Integer limit) {
    List<Player> players = playerService.getPlayersByTeamId(page, limit);

    return players.stream().map(PlayerInfo::createPlayerInfo).collect(Collectors.toList());
  }

  @GetMapping(ApiEndPoints.COUNTS)
  @ApiOperation(
      value = "Get total players count",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Something went wrong"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server Error")
      })
  public HttpResponse getPlayersCount() {
    return new HttpResponse(playerService.getPlayersCount());
  }

  @PutMapping
  @ApiOperation(
      value =
          "Update Player information. Only player owner can perform it. (user information is retrieved from JWT token)",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Something went wrong"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server Error")
      })
  public PlayerInfo updateTeam(
      @ApiParam("Player info update") @RequestBody PlayerUpdateParam playerUpdateParam) {
    Player player = playerService.getPlayerById(playerUpdateParam.getId());

    // validate player owner using JWT token
    PlayerUtils.validateIsPlayerOwnerAction(player);

    // update parameters to the player object
    playerUpdateParam.updatePlayer(player);

    // save player object in DB
    playerService.saveOrUpdate(player);
    return PlayerInfo.createPlayerInfo(player);
  }
}
