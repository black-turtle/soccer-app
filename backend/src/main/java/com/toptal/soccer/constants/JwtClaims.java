package com.toptal.soccer.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JwtClaims {
  UID("uid", "User id"),
  TID("tid", "Team id");

  private final String value;
  private final String details;
}
