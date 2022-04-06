import { ApiEndpoints } from "../constants/ApiEndPoints";
import { authHeader } from "../utils/auth.utils";
import HttpService from "./http.service";
import { APP_STATE_ACTIONS } from "../context/app-state-constants";

export default class UtilService {
  constructor(getState, applyAction, navigate) {
    this.getState = getState;
    this.applyAction = applyAction;
    this.navigate = navigate;
    this.api = new HttpService(applyAction).getInstance();
  }

  loadAllCountries() {
    this.api
      .get(ApiEndpoints.util.getAllCountry, { headers: authHeader() })
      .then((response) => {
        this.applyAction(APP_STATE_ACTIONS.SET_COUNTRIES, response.data);
      });
  }
}
