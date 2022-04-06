import InputModalLayout from "../../layouts/modal/InputModalLayout";
import React, { useState } from "react";
import NumberInput from "../NumberInput";
import useGlobalContext from "../../hooks/useGlobalContext";

export default function AddToTransferListModal({
  modalCloseAction,
  selectedPlayerId,
}) {
  const [price, setPrice] = useState(0);
  const { service } = useGlobalContext();

  const updateAction = () => {
    service.TransferService.createTransfer(selectedPlayerId, price);
  };

  return (
    <InputModalLayout
      header="Add player to transfer list"
      modalCLoseAction={modalCloseAction}
      updateAction={updateAction}
      buttonText="Add to transfer list"
    >
      <div className="space-y-6">
        <NumberInput
          label="Sell price"
          value={price}
          setValue={setPrice}
          minValue={1}
        />
      </div>
    </InputModalLayout>
  );
}
