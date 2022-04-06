import { InputValidationConst } from "../constants/Constants";

export default function TextInput({ label, text, setText }) {
  const key = label.replace(/\s/g, "");

  return (
    <div>
      <label
        htmlFor={key}
        className="text-left block text-sm font-medium text-gray-700"
      >
        {label}
      </label>
      <div className="mt-1 relative rounded-md shadow-sm">
        <input
          id={key}
          name={key}
          type="text"
          autoComplete={key}
          value={text}
          onChange={(e) => setText(e.target.value)}
          className="focus:ring-indigo-500 focus:border-indigo-500 block w-full pr-10 sm:text-sm border-gray-300 rounded-md"
          // validations
          required
          minLength={InputValidationConst.common.minLength}
          maxLength={InputValidationConst.common.maxLength}
        />
      </div>
    </div>
  );
}
