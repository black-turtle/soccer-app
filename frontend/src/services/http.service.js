import axios from "axios";
import { authHeader } from "../utils/auth.utils";
import { APP_STATE_ACTIONS } from "../context/app-state-constants";

const NO_AUTH_URL = ["/login", "signup"];

function isAuthTokenNeeded(requestUrl) {
  for (let idx in NO_AUTH_URL) {
    if (requestUrl.endsWith(NO_AUTH_URL[idx])) {
      return false;
    }
  }
  return true;
}

class HttpService {
  constructor(applyAction, navigate) {
    this.applyAction = applyAction;
    this.api = axios.create();
    this.navigate = navigate;

    // set default property
    this.api.defaults.timeout = 2500;

    // inject token in request interceptors
    this.api.interceptors.request.use(
      (request) => {
        if (isAuthTokenNeeded(request.url) && !request.headers.Authorization) {
          request.headers = authHeader();
        }
        return request;
      },
      (error) => error
    );

    // global exception handler in response
    // basically it adds the error object in context
    // error message is displayed by AlertMessage component which listens to this value
    this.api.interceptors.response.use(
      (response) => response,
      (error) => {
        this.applyAction(APP_STATE_ACTIONS.SET_ALERT, error);
        return Promise.reject(error);
      }
    );
  }

  getInstance() {
    return this.api;
  }
}

export default HttpService;
