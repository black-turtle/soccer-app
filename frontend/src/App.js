import React from "react";
import AlertMessage from "./components/AlertMessage";
import AppContextProvider from "./context/AppContextProvider";
import RoutesHandler from "./Routes";

/*
Depending on path, this component lazily loads a page Component
 */
function App() {
  // const isTokenValid = AuthService.isValidAuthPresent();
  return (
    <div>
      <AppContextProvider>
        <RoutesHandler />
        <AlertMessage />
      </AppContextProvider>
    </div>
  );
}

export default App;
