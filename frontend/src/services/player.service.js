import { ApiEndpoints } from "../constants/ApiEndPoints";
import { authHeader } from "../utils/auth.utils";
import HttpService from "./http.service";
import {
  APP_STATE_ACTIONS,
  APP_STATE_KEYS,
} from "../context/app-state-constants";
import { MessageType } from "../constants/Constants";
import { PAGE_LIMIT } from "../utils/common.utils";

export default class PlayerService {
  constructor(getState, applyAction, navigate) {
    this.getState = getState;
    this.applyAction = applyAction;
    this.navigate = navigate;
    this.api = new HttpService(applyAction).getInstance();
  }

  getPlayersInfo = (page, limit) => {
    // save page info in context, so that we can call this function without page info to get same page.
    if (!page) {
      page = this.getState(APP_STATE_KEYS.CURRENT_PAGE);
    } else {
      this.applyAction(APP_STATE_ACTIONS.SET_CURRENT_PAGE, page);
    }
    const params = { page: page - 1, limit: PAGE_LIMIT };

    // update player list
    this.api
      .get(ApiEndpoints.player.getPlayer, { params, headers: authHeader() })
      .then((response) => {
        this.applyAction(APP_STATE_ACTIONS.PLAYER_UPDATE_ACTION, response.data);
      });
  };

  getPlayersCount = () => {
    this.api
      .get(ApiEndpoints.player.getPlayerCount, { headers: authHeader() })
      .then((response) => {
        this.applyAction(
          APP_STATE_ACTIONS.PLAYER_CNT_ACTION,
          response.data.body
        );
      });
  };

  updatePlayerInfo = (id, firstName, lastName, country) => {
    const data = {};

    if (id) data.id = id;
    if (firstName) data.firstName = firstName;
    if (lastName) data.lastName = lastName;
    if (country) data.country = country;

    this.api
      .put(ApiEndpoints.player.updatePlayer, data, { headers: authHeader() })
      .then((response) => {
        // update the  edited players info in context
        const players = this.getState(APP_STATE_KEYS.PLAYERS);
        const newPlayers = players.map((player) =>
          player.playerId === id ? response.data : player
        );
        this.applyAction(APP_STATE_ACTIONS.PLAYER_UPDATE_ACTION, newPlayers);

        // display success message
        this.applyAction(APP_STATE_ACTIONS.SET_ALERT, {
          type: MessageType.SUCCESS,
          message: "Success!",
        });
      });
  };
}
