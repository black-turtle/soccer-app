import InputModalLayout from "../../layouts/modal/InputModalLayout";
import React, { useState } from "react";
import TextInput from "../TextInput";
import { APP_STATE_KEYS } from "../../context/app-state-constants";
import CountryInput from "../CountryInput";
import useGlobalContext from "../../hooks/useGlobalContext";

export default function EditPlayerInfoModal({
  modalCloseAction,
  selectedPlayerId,
}) {
  const { getState, service } = useGlobalContext();
  const players = getState(APP_STATE_KEYS.PLAYERS);

  const selectedPlayer = players.filter(
    (player) => player.playerId === selectedPlayerId
  )[0];

  const [firstName, setFirstName] = useState(
    selectedPlayer ? selectedPlayer.firstName : ""
  );
  const [lastName, setLastName] = useState(
    selectedPlayer ? selectedPlayer.lastName : ""
  );
  const [country, setCountry] = useState(
    selectedPlayer ? selectedPlayer.country : ""
  );

  const updateAction = () => {
    service.PlayerService.updatePlayerInfo(
      selectedPlayerId,
      firstName,
      lastName,
      country
    );
  };

  return (
    <InputModalLayout
      header="Edit Player information"
      modalCLoseAction={modalCloseAction}
      updateAction={updateAction}
    >
      <div className="space-y-6">
        <TextInput label="First Name" text={firstName} setText={setFirstName} />

        <TextInput label="Last Name" text={lastName} setText={setLastName} />

        <CountryInput country={country} setCountry={setCountry} />
      </div>
    </InputModalLayout>
  );
}
