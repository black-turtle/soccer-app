package com.toptal.soccer.service;

import com.toptal.soccer.data.model.Player;
import com.toptal.soccer.data.repository.PlayerRepository;
import com.toptal.soccer.exception.HttpException;
import com.toptal.soccer.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
  private final PlayerRepository playerRepository;

  /**
   * team_id is already stored in Jwt token claims. It uses that info to load Player information
   * from db
   *
   * @return List<Player>
   */
  public List<Player> getPlayersByTeamId(int page, int limit) {
    // get teamId from jwt token no DB call
    Integer teamId = AuthUtils.getTeamIdFromJwt();

    return playerRepository.findAllByTeamId(teamId, PageRequest.of(page, limit));
  }

  public List<Player> getPlayersByTeamId() {
    // get teamId from jwt token no DB call
    Integer teamId = AuthUtils.getTeamIdFromJwt();

    return playerRepository.findAllByTeamId(teamId);
  }

  public List<Player> getAllPlayers() {
    return playerRepository.findAll();
  }

  public Player getPlayerById(Integer id) {
    Optional<Player> player = playerRepository.findById(id);
    if (player.isPresent()) {
      return player.get();
    }
    throw new HttpException(
        String.format("Player not found with id: %s", id), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public void saveOrUpdate(Player player) {
    playerRepository.save(player);
  }

  public long getPlayersCount() {
    // get teamId from jwt token no DB call
    Integer teamId = AuthUtils.getTeamIdFromJwt();

    // return count by teamId
    return playerRepository.countByTeamId(teamId);
  }
}
