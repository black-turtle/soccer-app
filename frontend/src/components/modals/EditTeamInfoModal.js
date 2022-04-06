import InputModalLayout from "../../layouts/modal/InputModalLayout";
import React, { useState } from "react";
import TextInput from "../TextInput";
import CountryInput from "../CountryInput";
import { APP_STATE_KEYS } from "../../context/app-state-constants";
import useGlobalContext from "../../hooks/useGlobalContext";

export default function EditTeamInfoModal({ modalCloseAction }) {
  const { getState, service } = useGlobalContext();
  const currentTeamInfo = getState(APP_STATE_KEYS.TEAM);

  const [name, setName] = useState(currentTeamInfo ? currentTeamInfo.name : "");
  const [country, setCountry] = useState(
    currentTeamInfo ? currentTeamInfo.country : ""
  );

  const updateAction = () => {
    service.TeamService.updateTeamInfo(name, country);
  };

  return (
    <InputModalLayout
      header="Edit team information"
      modalCLoseAction={modalCloseAction}
      updateAction={updateAction}
    >
      <div className="space-y-6">
        <TextInput label="Team Name" text={name} setText={setName} />
        <CountryInput country={country} setCountry={setCountry} />
      </div>
    </InputModalLayout>
  );
}
