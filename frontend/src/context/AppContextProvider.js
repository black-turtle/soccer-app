import { createContext, useReducer } from "react";
import { appReducer } from "./app-reducer";
import { APP_INITIAL_VALUE, APP_STATE_KEYS } from "./app-state-constants";
import { AuthService } from "../services/auth.service";
import { useNavigate } from "react-router-dom";
import TeamService from "../services/team.service";
import PlayerService from "../services/player.service";
import TransferService from "../services/transfer.service";
import UtilService from "../services/util.service";

export const AppContext = createContext({});

export default function AppContextProvider({ children }) {
  const [state, dispatch] = useReducer(appReducer, APP_INITIAL_VALUE);
  const navigate = useNavigate();

  // function for getting data from context state
  const getState = (key) => {
    if (key in APP_STATE_KEYS) {
      return state[key];
    }
    console.error("Invalid state key: " + key);
  };

  // apply action used to perform a reducer action that changes context data
  const applyAction = (type, payload) => {
    const disPatchData = {};

    if (type) disPatchData.type = type;
    if (payload) disPatchData.payload = payload;

    dispatch(disPatchData);
  };

  // create service classes
  const service = {
    AuthService: new AuthService(getState, applyAction, navigate),
    TeamService: new TeamService(getState, applyAction, navigate),
    PlayerService: new PlayerService(getState, applyAction, navigate),
    TransferService: new TransferService(getState, applyAction, navigate),
    UtilService: new UtilService(getState, applyAction, navigate),
  };

  return (
    <AppContext.Provider
      value={{ getState: getState, applyAction: applyAction, service: service }}
    >
      {children}
    </AppContext.Provider>
  );
}
