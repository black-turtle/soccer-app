import { useEffect } from "react";
import cogoToast from "cogo-toast";
import { MessageType } from "../constants/Constants";
import { APP_STATE_KEYS } from "../context/app-state-constants";
import { useNavigate } from "react-router-dom";
import useGlobalContext from "../hooks/useGlobalContext";

const cogoToastOption = { position: "bottom-center" };

function handleApiError(error, navigate) {
  if (error.response) {
    if (error.response.status === 403) {
      cogoToast.error(
        "Session expired. Please login to continue.",
        cogoToastOption
      );
      navigate("/login");
    } else if (error.response.data) {
      cogoToast.error(error.response.data.message, cogoToastOption);
    } else {
      cogoToast.error("Server error", cogoToastOption);
    }
  } else if (error.message === "Network Error") {
    cogoToast.error("Network error. Server not running.", cogoToastOption);
    navigate("/server-down");
  }
  console.error("Error", error.response);
}

export default function AlertMessage() {
  const { getState } = useGlobalContext();
  const alert = getState(APP_STATE_KEYS.ALERT);
  const navigate = useNavigate();

  useEffect(() => {
    if (alert) {
      if (alert.type === MessageType.SUCCESS) {
        cogoToast.success(alert.message, cogoToastOption);
      } else {
        handleApiError(alert, navigate);
      }
    }
  }, [alert]);

  return <div></div>;
}
