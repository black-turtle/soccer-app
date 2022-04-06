import { ApiEndpoints } from "../constants/ApiEndPoints";
import { TOKEN_KEY, USER_NAME } from "../utils/auth.utils";
import jwt_decode from "jwt-decode";
import {
  APP_STATE_ACTIONS,
  APP_STATE_KEYS,
} from "../context/app-state-constants";
import { MessageType } from "../constants/Constants";
import HttpService from "./http.service";

export class AuthService {
  constructor(getState, applyAction, navigate) {
    this.getState = getState;
    this.applyAction = applyAction;
    this.navigate = navigate;
    this.api = new HttpService(applyAction).getInstance();
  }

  async signup() {
    const contextData = this.getState(APP_STATE_KEYS.USER);
    const username = contextData.userName;
    const password = contextData.password;
    const email = contextData.email;
    return this.api
      .post(ApiEndpoints.user.signUp, {
        username,
        email,
        password,
      })
      .then((response) =>
        this.loginSignUpSuccessHandler(username, response, "Signup success!")
      );
  }

  async login() {
    const contextData = this.getState(APP_STATE_KEYS.USER);
    const username = contextData.userName;
    const password = contextData.password;
    return this.api
      .post(ApiEndpoints.user.logIn, {
        username,
        password,
      })
      .then((response) =>
        this.loginSignUpSuccessHandler(username, response, "Login success!")
      );
  }

  loginSignUpSuccessHandler(username, response, message) {
    localStorage.setItem(USER_NAME, username);
    this.saveToken(response.data.body);
    this.applyAction(APP_STATE_ACTIONS.RESET_ALL_STATE);
    this.applyAction(APP_STATE_ACTIONS.SET_ALERT, {
      type: MessageType.SUCCESS,
      message: message,
    });
    this.navigate("/");
  }

  saveToken(token) {
    if (token) {
      localStorage.setItem(TOKEN_KEY, token);
    }
  }

  removeToken() {
    localStorage.removeItem(TOKEN_KEY);
  }

  isValidAuthPresent() {
    const token = localStorage.getItem(TOKEN_KEY);

    if (token) {
      const decodedToken = jwt_decode(token);
      if (Date.now() >= decodedToken.exp * 1000) {
        this.removeToken();
        return false;
      }
      return true;
    }

    return false;
  }
}
