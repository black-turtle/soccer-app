import Button from "./Button";
import { formatToCurrency } from "../utils/table.utils";
import { useEffect, useState } from "react";
import EditTeamInfoModal from "./modals/EditTeamInfoModal";
import { APP_STATE_KEYS } from "../context/app-state-constants";
import useGlobalContext from "../hooks/useGlobalContext";

function displaySingleInfo(name, value) {
  return (
    <div className="px-4 py-5 bg-white shadow rounded-lg overflow-hidden sm:p-6 bg-slate-200">
      <dt className="text-sm font-medium text-gray-500 truncate">{name}</dt>
      <dd className="mt-1 text-3xl font-semibold text-gray-900">{value}</dd>
    </div>
  );
}

function displayTeamInfo(teamInfo) {
  return (
    <>
      <dl className="mt-5 grid grid-cols-1 gap-5 sm:grid-cols-3">
        {displaySingleInfo("Name", teamInfo.name)}
        {displaySingleInfo("Country", teamInfo.country)}
        {displaySingleInfo("Team Budget", formatToCurrency(teamInfo.budget))}
        {displaySingleInfo("Team value", formatToCurrency(teamInfo.value))}
        {displaySingleInfo("Total players", teamInfo.totalPlayers)}
      </dl>
    </>
  );
}

export default function TeamInfo() {
  const { getState, applyAction, service } = useGlobalContext();
  const [showEditModal, setShowEditModal] = useState(false);

  const teamInfo = getState(APP_STATE_KEYS.TEAM);

  useEffect(() => {
    // load team info only first time. after that context will handle the updates
    if (!teamInfo) {
      service.TeamService.getTeamInfo();
    }
  }, [teamInfo]);

  return (
    <div className="m-5">
      <h1 className="w-full text-center text-4xl font-bold leading-tight text-gray-900">
        Team information
      </h1>

      {teamInfo && displayTeamInfo(teamInfo)}
      {teamInfo && (
        <div className="w-40  my-5 mx-auto">
          <Button text="Edit team" action={() => setShowEditModal(true)} />
        </div>
      )}

      {showEditModal && (
        <EditTeamInfoModal modalCloseAction={() => setShowEditModal(false)} />
      )}
    </div>
  );
}
