package com.toptal.soccer.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlayerType {
  GOAL_KEEPER(3),
  DEFENDER(6),
  MID_FIELDER(6),
  ATTACKER(5);

  private final int initialCount;
}
