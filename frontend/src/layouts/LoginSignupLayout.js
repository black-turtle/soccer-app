export default function LoginSignupLayout({ text, children }) {
  return (
    <div className="min-h-85-vh flex flex-col justify-center py-12 sm:px-6 lg:px-8 .shadow-xl">
      <div className="sm:mx-auto sm:w-full sm:max-w-md">
        <img
          className="mx-auto h-12 w-auto"
          src="/logo512.png"
          alt="app logo"
        />
        <h2 className="mt-6 text-center text-3xl font-bold text-gray-900">
          {text}
        </h2>
      </div>

      <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
        <div className="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
          {children}
        </div>
      </div>
    </div>
  );
}
