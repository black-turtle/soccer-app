package com.toptal.soccer.rest.controller;

import com.toptal.soccer.constants.ApiEndPoints;
import com.toptal.soccer.data.model.Player;
import com.toptal.soccer.data.model.Transfer;
import com.toptal.soccer.rest.dto.request.TransferRequestParam;
import com.toptal.soccer.rest.dto.response.HttpResponse;
import com.toptal.soccer.rest.dto.response.TransferInfo;
import com.toptal.soccer.service.PlayerService;
import com.toptal.soccer.service.TransferService;
import com.toptal.soccer.utils.PlayerUtils;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiEndPoints.TRANSFER_BASE)
@Api(tags = "transfers", description = "Api for player transfer management")
@RequiredArgsConstructor
public class TransferController {
  private final TransferService transferService;
  private final PlayerService playerService;

  @GetMapping
  @ApiOperation(
      value = "Get Transfer list (uses pagination)",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Something went wrong"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server Error")
      })
  public List<TransferInfo> getTransfers(
      @ApiParam("page") @RequestParam(required = false, defaultValue = "0") Integer page,
      @ApiParam("Limit") @RequestParam(required = false, defaultValue = "" + Integer.MAX_VALUE)
          Integer limit) {
    List<Transfer> transfers = transferService.getTransfers(page, limit);
    return transfers.stream().map(TransferInfo::createTransferInfo).collect(Collectors.toList());
  }

  @GetMapping(ApiEndPoints.COUNTS)
  @ApiOperation(
      value = "Get total transfers count",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Something went wrong"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server Error")
      })
  public HttpResponse getTransfersCount() {
    return new HttpResponse(transferService.getTransfersCount());
  }

  @PostMapping
  @ApiOperation(
      value =
          "Create a new transfer request. This transfer request must be placed by the player owner. "
              + "(user information is retrieved from JWT token)",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "true"),
        @ApiResponse(code = 400, message = "Something went wrong"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server Error")
      })
  public HttpResponse createTransfer(
      @ApiParam("Player info update") @RequestBody TransferRequestParam transferRequestParam) {
    Player player = playerService.getPlayerById(transferRequestParam.getPlayerId());

    // validate player owner using JWT token
    PlayerUtils.validateIsPlayerOwnerAction(player);

    // create New Transfer
    Transfer transfer = transferService.createNewTransfer(player, transferRequestParam.getPrice());
    return new HttpResponse(true, HttpStatus.CREATED);
  }

  @PostMapping(ApiEndPoints.APPLY_TRANSFER)
  @ApiOperation(
      value =
          "This will validate player transfer, move the player to current user's team & will update team budgets",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "true"),
        @ApiResponse(code = 400, message = "Player is already sold"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server Error")
      })
  public HttpResponse transfer(@ApiParam("Transfer Id") @PathVariable Integer transferId) {
    transferService.transferPlayer(transferId);

    return new HttpResponse(true);
  }
}
