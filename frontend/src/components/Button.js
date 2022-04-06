const actionOnGoingText = "Submitting...";
export default function Button({ type, text, action, isFormSubmitting }) {
  return (
    <div>
      <button
        type={type ? type : "button"}
        onClick={() => action && action()}
        disabled={isFormSubmitting}
        className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 hover:cursor-pointer disabled:opacity-70 disabled:cursor-not-allowed disabled:text-white hover-focus-animation"
      >
        {isFormSubmitting ? actionOnGoingText : text}
      </button>
    </div>
  );
}
