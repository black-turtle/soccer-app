import { Link } from "react-router-dom";

export default function ServerDownPage() {
  return (
    <>
      <div className="min-h-full pt-16 pb-12 flex flex-col bg-white">
        <main className="flex-grow flex flex-col justify-center max-w-7xl w-full mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex-shrink-0 flex justify-center">
            <a href="/" className="inline-flex">
              <span className="sr-only">Workflow</span>
              <img className="h-12 w-auto" src="/logo512.png" alt="logo" />
            </a>
          </div>
          <div className="py-16">
            <div className="text-center">
              <p className="text-sm font-semibold text-indigo-600 uppercase tracking-wide">
                Network error
              </p>
              <h1 className="mt-2 text-4xl font-extrabold text-gray-900 tracking-tight sm:text-5xl">
                Server down
              </h1>
              <p className="mt-2 text-base text-gray-500">
                Please try again later.
              </p>
              <div className="mt-6">
                <a className="text-base font-medium text-indigo-600 hover:text-indigo-500">
                  <Link to="/login">
                    try now<span aria-hidden="true"> &rarr;</span>{" "}
                  </Link>
                </a>
              </div>
            </div>
          </div>
        </main>
      </div>
    </>
  );
}
