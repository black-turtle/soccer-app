import {
  APP_INITIAL_VALUE,
  APP_STATE_ACTIONS,
  APP_STATE_KEYS,
} from "./app-state-constants";

export const appReducer = (state, action) => {
  const { type, payload } = action;
  switch (type) {
    // user actions
    case APP_STATE_ACTIONS.USER_UPDATE_ACTION:
      if (payload) {
        let newUser = state[APP_STATE_KEYS.USER];
        newUser = newUser ? { ...newUser, ...payload } : payload;
        return { ...state, [APP_STATE_KEYS.USER]: newUser };
      } else {
        return { ...state, [APP_STATE_KEYS.USER]: null };
      }

    // team actions
    case APP_STATE_ACTIONS.TEAM_UPDATE_ACTION:
      return { ...state, [APP_STATE_KEYS.TEAM]: action.payload };

    // player actions
    case APP_STATE_ACTIONS.PLAYER_UPDATE_ACTION:
      return { ...state, [APP_STATE_KEYS.PLAYERS]: action.payload };

    case APP_STATE_ACTIONS.PLAYER_CNT_ACTION:
      return { ...state, [APP_STATE_KEYS.TOTAL_PLAYERS]: action.payload };

    // transfer actions
    case APP_STATE_ACTIONS.TRANSFER_LIST_UPDATE_ACTION:
      return { ...state, [APP_STATE_KEYS.PLAYERS]: action.payload };

    case APP_STATE_ACTIONS.TRANSFER_LIST_CNT_UPDATE_ACTION:
      return { ...state, [APP_STATE_KEYS.TOTAL_TRANSFERS]: action.payload };

    // alert actions
    case APP_STATE_ACTIONS.SET_ALERT:
      return { ...state, [APP_STATE_KEYS.ALERT]: payload };

    // set page action
    case APP_STATE_ACTIONS.SET_CURRENT_PAGE:
      return { ...state, [APP_STATE_KEYS.CURRENT_PAGE]: payload };

    // set countries
    case APP_STATE_ACTIONS.SET_COUNTRIES:
      return { ...state, [APP_STATE_KEYS.COUNTRY]: payload };

    // reset all state
    case APP_STATE_ACTIONS.RESET_ALL_STATE:
      return { ...APP_INITIAL_VALUE };

    default:
      console.error("No reducer found for action: ", action);
      return state;
  }
};
