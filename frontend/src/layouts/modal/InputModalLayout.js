import React from "react";
import Button from "../../components/Button";
import BaseModalLayout from "./BaseModalLayout";

export default function InputModalLayout({
  children,
  updateAction,
  modalCLoseAction,
  header,
  buttonText,
}) {
  const onFormSubmit = (e) => {
    e.preventDefault();
    updateAction();
    modalCLoseAction();
  };
  return (
    <BaseModalLayout modalCLoseAction={modalCLoseAction}>
      <form
        onSubmit={(e) => onFormSubmit(e)}
        className="inline-block align-bottom bg-white rounded-lg p-4 text-left overflow-hidden shadow-xl transform transition-all mb-16 sm:my-8 sm:align-middle sm:max-w-xl sm:w-full sm:p-6"
      >
        <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
          {header && (
            <div className="w-full mb-8 text-center text-3xl font-bold  text-gray-900">
              {header}
            </div>
          )}
          {children}
        </div>

        <div className="mt-6 w-40 mx-auto">
          <Button type="submit" text={buttonText ? buttonText : "Change"} />
        </div>
      </form>
    </BaseModalLayout>
  );
}
