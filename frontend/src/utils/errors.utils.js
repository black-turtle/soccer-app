import { APP_STATE_ACTIONS } from "../context/app-state-constants";

export function setMessageType(applyAction, type) {
  applyAction(APP_STATE_ACTIONS.SET_MESSAGE_TYPE, type);
}

export function setMessage(applyAction, message) {
  applyAction(APP_STATE_ACTIONS.SET_MESSAGE, message);
}

export const ApiErrorHandler = (error, applyAction, navigate) => {
  if (error.response) {
    if (error.response.status === 403) {
      setMessage(applyAction, "Session expired. Redirecting to login page.");
      navigate("/login");
    } else {
      if (error.response.data) {
        setMessage(applyAction, error.response.data.message);
      } else {
        setMessage(applyAction, "Server error");
      }
    }
  }
  console.error("Error", error.response.data);
};
