import {
  APP_STATE_ACTIONS,
  APP_STATE_KEYS,
} from "../context/app-state-constants";
import useGlobalContext from "../hooks/useGlobalContext";

export default function EmailInput() {
  const { getState, applyAction } = useGlobalContext();
  const data = getState(APP_STATE_KEYS.USER);
  const email = data ? data.email : "";
  return (
    <div>
      <label
        htmlFor="email"
        className="block text-sm font-medium text-gray-700"
      >
        Email address
      </label>
      <div className="mt-1">
        <input
          id="email"
          name="email"
          type="email"
          autoComplete="email"
          value={email}
          onChange={(e) =>
            applyAction(APP_STATE_ACTIONS.USER_UPDATE_ACTION, {
              email: e.target.value,
            })
          }
          required
          className="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
        />
      </div>
    </div>
  );
}
