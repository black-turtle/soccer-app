import React, { useState } from "react";
import UserNameInput from "../components/UserNameInput";
import PasswordInput from "../components/PasswordInput";
import Button from "../components/Button";
import LoginSignupLayout from "../layouts/LoginSignupLayout";
import EmailInput from "../components/EmailInput";
import { useNavigate } from "react-router-dom";
import { APP_STATE_ACTIONS } from "../context/app-state-constants";
import useGlobalContext from "../hooks/useGlobalContext";

export default function Signup() {
  const { service, applyAction } = useGlobalContext();
  const navigate = useNavigate();
  const [isFormSubmitting, setIsFormSubmitting] = useState(false);

  const clearUserData = () => {
    applyAction(APP_STATE_ACTIONS.USER_UPDATE_ACTION, null);
    navigate("/login");
  };

  const onSignUp = async (e) => {
    e.preventDefault();
    try {
      setIsFormSubmitting(true);
      await service.AuthService.signup();
    } finally {
      setIsFormSubmitting(false);
    }
  };

  return (
    <LoginSignupLayout text="Create a new Account">
      <form className="space-y-6" onSubmit={(e) => onSignUp(e)}>
        <EmailInput />
        <UserNameInput />
        <PasswordInput />
        <Button
          text="Register"
          type="submit"
          isFormSubmitting={isFormSubmitting}
        />
        <p className="mt-5 text-center text-sm text-gray-600">
          Already have a account?
          <span
            className="font-medium text-indigo-600 hover:text-indigo-500"
            onClick={() => clearUserData()}
          >
            Login
          </span>
        </p>
      </form>
    </LoginSignupLayout>
  );
}
