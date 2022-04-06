import {
  APP_STATE_ACTIONS,
  APP_STATE_KEYS,
} from "../context/app-state-constants";
import { InputValidationConst } from "../constants/Constants";
import useGlobalContext from "../hooks/useGlobalContext";

export default function UserNameInput() {
  const { getState, applyAction } = useGlobalContext();
  const data = getState(APP_STATE_KEYS.USER);
  const userName = data ? data.userName : "";
  return (
    <div>
      <label
        htmlFor="username"
        className="block text-sm font-medium text-gray-700"
      >
        User Name
      </label>
      <div className="mt-1 relative rounded-md shadow-sm">
        <input
          id="username"
          name="username"
          type="text"
          autoComplete="username"
          value={userName}
          onChange={(e) =>
            applyAction(APP_STATE_ACTIONS.USER_UPDATE_ACTION, {
              userName: e.target.value,
            })
          }
          className="focus:ring-indigo-500 focus:border-indigo-500 block w-full pr-10 sm:text-sm border-gray-300 rounded-md"
          // validations
          required
          minLength={InputValidationConst.username.minLength}
          maxLength={InputValidationConst.username.maxLength}
        />
      </div>
    </div>
  );
}
