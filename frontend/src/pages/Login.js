import React, { useState } from "react";
import UserNameInput from "../components/UserNameInput";
import PasswordInput from "../components/PasswordInput";
import Button from "../components/Button";
import LoginSignupLayout from "../layouts/LoginSignupLayout";
import { APP_STATE_ACTIONS } from "../context/app-state-constants";
import { useNavigate } from "react-router-dom";
import useGlobalContext from "../hooks/useGlobalContext";

export default function LogIn() {
  const { service, applyAction } = useGlobalContext();
  const navigate = useNavigate();
  const [isFormSubmitting, setIsFormSubmitting] = useState(false);

  const clearUserData = () => {
    applyAction(APP_STATE_ACTIONS.USER_UPDATE_ACTION, null);
    navigate("/signup");
  };

  const onLogin = async (e) => {
    e.preventDefault();

    try {
      setIsFormSubmitting(true);
      await service.AuthService.login();
    } finally {
      setIsFormSubmitting(false);
    }
  };

  return (
    <LoginSignupLayout text="Sign in to your account">
      <form className="space-y-6" onSubmit={(e) => onLogin(e)}>
        <UserNameInput />
        <PasswordInput />
        <Button
          type="submit"
          text="Log in"
          isFormSubmitting={isFormSubmitting}
        />
        {getSignupMessage(clearUserData)}
      </form>
    </LoginSignupLayout>
  );
}

function getSignupMessage(clearUserData) {
  return (
    <div className="mt-5 text-center text-sm text-gray-600">
      Don't have a account?
      <span
        className="font-medium text-indigo-600 hover:text-indigo-500"
        onClick={() => clearUserData()}
      >
        Get started
      </span>
    </div>
  );
}
