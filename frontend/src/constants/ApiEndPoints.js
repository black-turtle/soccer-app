export class ApiEndpoints {
  // static BASE_URL = "http://localhost:8080/api/v1";
  static BASE_URL = "http://localhost:8080/api/v1";

  static user = {
    logIn: `${ApiEndpoints.BASE_URL}/users/login`,
    signUp: `${ApiEndpoints.BASE_URL}/users/signup`,
  };

  static team = {
    getTeam: `${ApiEndpoints.BASE_URL}/teams`,
    updateTeam: `${ApiEndpoints.BASE_URL}/teams`,
  };

  static player = {
    getPlayer: `${ApiEndpoints.BASE_URL}/players`,
    getPlayerCount: `${ApiEndpoints.BASE_URL}/players/count`,
    updatePlayer: `${ApiEndpoints.BASE_URL}/players`,
  };

  static transfer = {
    getTransferList: `${ApiEndpoints.BASE_URL}/transfers`,
    getTransferCount: `${ApiEndpoints.BASE_URL}/transfers/count`,
    createTransferList: `${ApiEndpoints.BASE_URL}/transfers`,
    applyTransfer: `${ApiEndpoints.BASE_URL}/transfers/{transferId}/apply`,
  };

  static util = {
    getAllCountry: `${ApiEndpoints.BASE_URL}/utils/countries`,
  };
}
