package com.toptal.soccer.service;

import com.toptal.soccer.data.model.Player;
import com.toptal.soccer.data.model.Team;
import com.toptal.soccer.data.model.Transfer;
import com.toptal.soccer.data.repository.PlayerRepository;
import com.toptal.soccer.data.repository.TeamRepository;
import com.toptal.soccer.data.repository.TransferRepository;
import com.toptal.soccer.exception.HttpException;
import com.toptal.soccer.utils.PlayerUtils;
import com.toptal.soccer.utils.TeamUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransferService {
  private final TransferRepository transferRepository;
  private final PlayerRepository playerRepository;
  private final TeamService teamService;
  private final TeamRepository teamRepository;

  public Transfer createNewTransfer(Player player, long price) {
    Transfer transfer = Transfer.createNewTransfer();

    // update values
    transfer.setPrice(price);

    // set relationship
    player.setTransfer(transfer);
    transfer.setPlayer(player);

    // transfer is automatically saved by CASCADE Db operation
    return playerRepository.save(player).getTransfer();
  }

  public List<Transfer> getTransfers(int page, int limit) {
    return transferRepository
        .findAll(PageRequest.of(page, limit))
        .get()
        .collect(Collectors.toList());
  }

  @Transactional
  public void transferPlayer(Integer transferId) {
    Transfer transfer = getTransfer(transferId);
    Team currentTeam = transfer.getPlayer().getTeam();
    Team newTeam = teamService.getCurrentTeam();

    TeamUtils.validateBuyerTeam(transfer, newTeam);
    TeamUtils.validateTeamHasEnoughBudget(newTeam, transfer);

    // update budget
    newTeam.setBudget(newTeam.getBudget() - transfer.getPrice());
    currentTeam.setBudget(currentTeam.getBudget() + transfer.getPrice());

    // update reference in player
    Player player = transfer.getPlayer();
    player.setTeam(newTeam);
    player.setTransfer(null);
    player.setValue(PlayerUtils.getRandomNewValue(transfer.getPrice()));

    // update player & team reference
    newTeam.getPlayers().add(player);
    currentTeam.getPlayers().remove(player);

    // apply db operations
    playerRepository.save(player);
    teamRepository.save(newTeam);
    teamRepository.save(currentTeam);
    transferRepository.deleteById(transferId);
  }

  public Transfer getTransfer(Integer transferId) {
    try {
      Transfer transfer = transferRepository.getById(transferId);
      // validate all entity present
      transfer.getPlayer().getTeam().getId();
      return transfer;
    } catch (Exception ex) {
      throw new HttpException("This Player is already sold", HttpStatus.BAD_REQUEST);
    }
  }

  public long getTransfersCount() {
    return transferRepository.count();
  }
}
