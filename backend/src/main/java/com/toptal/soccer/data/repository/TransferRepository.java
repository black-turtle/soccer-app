package com.toptal.soccer.data.repository;

import com.toptal.soccer.data.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {
  Transfer findByPlayerId(int playerId);
}
