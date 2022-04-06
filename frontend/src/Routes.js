import { Navigate, Route, Routes } from "react-router-dom";
import React, { lazy, Suspense } from "react";
import useGlobalContext from "./hooks/useGlobalContext";

const Home = lazy(() => import("./pages/Home"));
const Login = lazy(() => import("./pages/Login"));
const Signup = lazy(() => import("./pages/Signup"));
const TransferList = lazy(() => import("./pages/TransferList"));
const NotFoundPage = lazy(() => import("./pages/NotFoundPage"));
const ServerDownPage = lazy(() => import("./pages/ServerDownPage"));

function AuthGuard({ isAuthenticated, component }) {
  return isAuthenticated ? component : <Navigate to="/login" />;
}

export default function RoutesHandler() {
  const { service } = useGlobalContext();
  const isAuthenticated = service.AuthService.isValidAuthPresent();
  return (
    <Suspense fallback={<div>Loading...</div>}>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        // Guarded Routes
        <Route
          path="/"
          element={
            <AuthGuard component={<Home />} isAuthenticated={isAuthenticated} />
          }
        />
        <Route
          path="/transfer-list"
          element={
            <AuthGuard
              component={<TransferList />}
              isAuthenticated={isAuthenticated}
            />
          }
        />
        <Route path="/server-down" element={<ServerDownPage />} />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </Suspense>
  );
}
