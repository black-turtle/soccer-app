import React from "react";
import MessageModalLayout from "../../layouts/modal/MessageModalLayout";
import { ExclamationIcon } from "@heroicons/react/outline";
import useGlobalContext from "../../hooks/useGlobalContext";

export default function TransferModal({
  modalCloseAction,
  selectedTransferId,
}) {
  const { service } = useGlobalContext();

  const updateAction = () => {
    service.TransferService.applyTransfer(selectedTransferId);
  };

  return (
    <MessageModalLayout
      actionText={"Buy"}
      modalCLoseAction={modalCloseAction}
      updateAction={updateAction}
    >
      <div>
        <div className="mx-auto flex items-center justify-center h-12 w-12 rounded-full bg-yellow-100">
          <ExclamationIcon
            className="h-6 w-6 text-yellow-600"
            aria-hidden="true"
          />
        </div>
        <div className="mt-3 text-center sm:mt-5">
          <div className="mt-2">
            <div className="text-sm text-gray-500">
              <div>
                <p>Do you really want to buy this player? </p>
                <p className="text-bold mt-3">
                  Note: Player price will be deducted from your team budget
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </MessageModalLayout>
  );
}
