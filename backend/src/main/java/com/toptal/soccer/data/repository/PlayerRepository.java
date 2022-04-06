package com.toptal.soccer.data.repository;

import com.toptal.soccer.data.model.Player;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
  List<Player> findAllByTeamId(int teamId);

  List<Player> findAllByTeamId(int teamId, Pageable pageable);

  long countByTeamId(Integer teamId);
}
