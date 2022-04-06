package com.toptal.soccer.service;

import com.toptal.soccer.data.model.Team;
import com.toptal.soccer.data.repository.TeamRepository;
import com.toptal.soccer.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
  private final TeamRepository teamRepository;

  /**
   * team_id is already stored in Jwt token claims. It uses that info to load team information from
   * db
   *
   * @return Team
   */
  public Team getCurrentTeam() {
    // get teamId from jwt token no DB call
    Integer teamId = AuthUtils.getTeamIdFromJwt();

    // get team data from db by teamId
    return teamRepository.findByUserId(teamId);
  }

  public void saveOrUpdate(Team team) {
    teamRepository.save(team);
  }
}
