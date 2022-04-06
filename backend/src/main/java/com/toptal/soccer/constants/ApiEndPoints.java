package com.toptal.soccer.constants;

public interface ApiEndPoints {
  String BASE_V1 = "/api/v1";

  // Controller base paths
  String HEALTH_BASE = BASE_V1 + "/health";
  String USERS_BASE = BASE_V1 + "/users";
  String TEAMS_BASE = BASE_V1 + "/teams";
  String PLAYERS_BASE = BASE_V1 + "/players";
  String TRANSFER_BASE = BASE_V1 + "/transfers";
  String UTILS_BASE = BASE_V1 + "/utils";

  // rest paths
  String USERS_LOGIN = "/login";
  String USERS_SIGNUP = "/signup";
  String COUNTS = "/count";
  String APPLY_TRANSFER = "/{transferId}/apply";

  static String getFullPath(String base, String path) {
    return (path == null) ? base : base + path;
  }
}
