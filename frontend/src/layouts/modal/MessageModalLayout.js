import React from "react";
import BaseModalLayout from "./BaseModalLayout";

export default function MessageModalLayout({
  children,
  updateAction,
  modalCLoseAction,
  actionText,
}) {
  return (
    <BaseModalLayout modalCLoseAction={modalCLoseAction}>
      <div className="relative inline-block align-bottom bg-white rounded-lg px-4 pt-5 pb-4 text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-sm sm:w-full sm:p-6">
        {children}
        <div className="mt-5 sm:mt-6">
          <button
            type="button"
            className="inline-flex justify-center w-full rounded-md border border-transparent shadow-sm px-4 py-2 bg-indigo-600 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:text-sm"
            onClick={() => {
              updateAction();
              modalCLoseAction();
            }}
          >
            {actionText}
          </button>
        </div>
      </div>
    </BaseModalLayout>
  );
}
