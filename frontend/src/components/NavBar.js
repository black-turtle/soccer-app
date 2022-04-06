/* This example requires Tailwind CSS v2.0+ */
import Button from "./Button";
import {Link, useLocation, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import {APP_STATE_ACTIONS} from "../context/app-state-constants";
import {USER_NAME} from "../utils/auth.utils";
import useGlobalContext from "../hooks/useGlobalContext";

function getStyle(isActive) {
  if (isActive) {
    return "border-indigo-500 text-gray-900 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium";
  } else {
    return "border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium";
  }
}

export default function NavBar() {
  const location = useLocation();
  const navigate = useNavigate();
  const [path, setPath] = useState("/");
  const { service, applyAction } = useGlobalContext();
  const userName = localStorage.getItem(USER_NAME);

  const logoutAction = () => {
    service.AuthService.removeToken();
    navigate("/login");
  };

  useEffect(() => {
    setPath(location.pathname);
    applyAction(APP_STATE_ACTIONS.SET_CURRENT_PAGE, 1);
  }, [path]);

  return (
    <div className="mx-auto px-4 sm:px-6 lg:px-8">
      <div className="flex justify-between h-16">
        <div className="flex">
          <div className="flex-shrink-0 flex items-center">
            <img
              className="mx-auto h-12 w-auto"
              src="/logo512.png"
              alt="app logo"
            />
            <span className="hidden sm:ml-3 sm:flex text-2xl font-bold leading-tight text-gray-900">
              Soccer game App
            </span>
          </div>
          <div className=" ml-6 flex sm:space-x-8 space-x-4">
            <span className={getStyle(path === "/")}>
              <Link to="/">Home</Link>
            </span>{" "}
            <span className={getStyle(path === "/transfer-list")}>
              <Link to="/transfer-list">Transfer List</Link>
            </span>
          </div>
        </div>
        <div className="ml-6 sm:flex sm:items-center">
          <span className="mx-3 text-2xl font-bold leading-tight text-gray-900">
            {userName}
          </span>

          <Button text="Log out" action={logoutAction} />
        </div>
      </div>
    </div>
  );
}
