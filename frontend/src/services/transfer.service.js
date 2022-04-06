import { ApiEndpoints } from "../constants/ApiEndPoints";
import { authHeader } from "../utils/auth.utils";
import HttpService from "./http.service";
import {
  APP_STATE_ACTIONS,
  APP_STATE_KEYS,
} from "../context/app-state-constants";
import { MessageType } from "../constants/Constants";
import { PAGE_LIMIT } from "../utils/common.utils";

export default class TransferService {
  constructor(getState, applyAction, navigate) {
    this.getState = getState;
    this.applyAction = applyAction;
    this.navigate = navigate;
    this.api = new HttpService(applyAction).getInstance();
  }

  getTransferList = (page) => {
    // save page info in context, so that we can call this function without page info to get same page.
    if (!page) {
      page = this.getState(APP_STATE_KEYS.CURRENT_PAGE);
    } else {
      this.applyAction(APP_STATE_ACTIONS.SET_CURRENT_PAGE, page);
    }
    const params = { page: page - 1, limit: PAGE_LIMIT };

    this.api
      .get(ApiEndpoints.transfer.getTransferList, {
        params,
        headers: authHeader(),
      })
      .then((response) => {
        this.applyAction(APP_STATE_ACTIONS.PLAYER_UPDATE_ACTION, response.data);
      });
  };

  getTransferCount = () => {
    this.api
      .get(ApiEndpoints.transfer.getTransferCount, { headers: authHeader() })
      .then((response) => {
        this.applyAction(
          APP_STATE_ACTIONS.TRANSFER_LIST_CNT_UPDATE_ACTION,
          response.data.body
        );
      });
  };

  createTransfer = (playerId, price) => {
    const data = {};
    if (playerId) data.playerId = playerId;
    if (price) data.price = parseInt(price);

    this.api
      .post(ApiEndpoints.transfer.createTransferList, data, {
        headers: authHeader(),
      })
      .then(() => {
        // update the  edited players info in context
        const players = this.getState(APP_STATE_KEYS.PLAYERS);
        const newPlayers = players.map((player) => {
          if (player.playerId === playerId) {
            player.canTransfer = true;
          }
          return player;
        });

        this.applyAction(APP_STATE_ACTIONS.PLAYER_UPDATE_ACTION, newPlayers);

        // display message
        this.applyAction(APP_STATE_ACTIONS.SET_ALERT, {
          type: MessageType.SUCCESS,
          message: "Success!",
        });
      });
  };

  applyTransfer = (transferId) => {
    const data = {};
    this.api
      .post(
        ApiEndpoints.transfer.applyTransfer.replace("{transferId}", transferId),
        data,
        {
          headers: authHeader(),
        }
      )
      .then(() => {
        this.resetTransferAndPlayers();

        this.applyAction(APP_STATE_ACTIONS.SET_ALERT, {
          type: MessageType.SUCCESS,
          message: "Success!",
        });
      })
      .catch((e) => {
        this.resetTransferAndPlayers();
      });
  };

  resetTransferAndPlayers() {
    // update transfer list(only current page) & count
    this.getTransferList();
    this.getTransferCount();

    //  force to reload
    this.applyAction(APP_STATE_ACTIONS.TEAM_UPDATE_ACTION, null);
    this.applyAction(APP_STATE_ACTIONS.PLAYER_UPDATE_ACTION, null);
  }
}
