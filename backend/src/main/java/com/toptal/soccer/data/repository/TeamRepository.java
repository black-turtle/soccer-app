package com.toptal.soccer.data.repository;

import com.toptal.soccer.data.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
  Team findByUserId(int userId);
}
