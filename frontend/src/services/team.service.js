import { ApiEndpoints } from "../constants/ApiEndPoints";
import { authHeader } from "../utils/auth.utils";
import HttpService from "./http.service";
import { APP_STATE_ACTIONS } from "../context/app-state-constants";
import { MessageType } from "../constants/Constants";

export default class TeamService {
  constructor(getState, applyAction, navigate) {
    this.getState = getState;
    this.applyAction = applyAction;
    this.navigate = navigate;
    this.api = new HttpService(applyAction).getInstance();
  }

  getTeamInfo = () => {
    this.api
      .get(ApiEndpoints.team.getTeam, { headers: authHeader() })
      .then((response) => {
        this.applyAction(APP_STATE_ACTIONS.TEAM_UPDATE_ACTION, response.data);
      });
  };

  updateTeamInfo = (name, country) => {
    const data = {};
    if (name) data.name = name;
    if (country) data.country = country;

    this.api
      .put(ApiEndpoints.team.updateTeam, data, { headers: authHeader() })
      .then((response) => {
        this.applyAction(APP_STATE_ACTIONS.TEAM_UPDATE_ACTION, response.data);
        this.applyAction(APP_STATE_ACTIONS.SET_ALERT, {
          type: MessageType.SUCCESS,
          message: "Success!",
        });
      });
  };
}
