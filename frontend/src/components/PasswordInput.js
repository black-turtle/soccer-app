import { useState } from "react";
import { EyeIcon, EyeOffIcon } from "@heroicons/react/outline";
import { InputValidationConst } from "../constants/Constants";
import {
  APP_STATE_ACTIONS,
  APP_STATE_KEYS,
} from "../context/app-state-constants";
import useGlobalContext from "../hooks/useGlobalContext";

export default function PasswordInput() {
  const [showPassword, setShowPassword] = useState(false);
  const toggleShowPasswordFunc = (e) => {
    e.preventDefault();
    setShowPassword(!showPassword);
  };

  const { getState, applyAction } = useGlobalContext();
  const data = getState(APP_STATE_KEYS.USER);
  const password = data ? data.password : "";
  return (
    <div>
      <label
        htmlFor="password"
        className="block text-sm font-medium text-gray-700"
      >
        Password
      </label>
      <div className="mt-1 relative rounded-md shadow-sm">
        <input
          id="password"
          name="password"
          type={showPassword ? "text" : "password"}
          autoComplete="password"
          value={password}
          onChange={(e) =>
            applyAction(APP_STATE_ACTIONS.USER_UPDATE_ACTION, {
              password: e.target.value,
            })
          }
          className="focus:ring-indigo-500 focus:border-indigo-500 block w-full pr-10 sm:text-sm border-gray-300 rounded-md"
          //validations
          required
          minLength={InputValidationConst.password.minLength}
          maxLength={InputValidationConst.password.maxLength}
        />
        <div
          className="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-auto"
          onClick={(e) => toggleShowPasswordFunc(e)}
        >
          {showPassword ? (
            <EyeIcon className="h-5 w-5 text-gray-400" aria-hidden="true" />
          ) : (
            <EyeOffIcon className="h-5 w-5 text-gray-400" aria-hidden="true" />
          )}
        </div>
      </div>
    </div>
  );
}
