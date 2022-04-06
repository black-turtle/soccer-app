import { useContext } from "react";
import { AppContext } from "../context/AppContextProvider";

export default function useGlobalContext() {
  const { getState, applyAction, service } = useContext(AppContext);
  return { getState, applyAction, service };
}
