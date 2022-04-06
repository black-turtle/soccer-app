import { classNames } from "../utils/common.utils";

export default function PaginationHandler({
  start,
  end,
  total,
  previousClickAction,
  nextClickAction,
}) {
  return (
    <nav
      className="bg-white px-4 py-3 flex items-center justify-between border-t border-gray-200 sm:px-6"
      aria-label="Pagination"
    >
      <div className="hidden sm:block">
        <p className="text-sm text-gray-700">
          Showing <span className="font-medium">{start}</span> to{" "}
          <span className="font-medium">{Math.min(end, total)}</span> of{" "}
          <span className="font-medium">{total}</span> results
        </p>
      </div>
      <div className="flex-1 flex justify-between sm:justify-end">
        <button
          onClick={previousClickAction}
          className={classNames(
            "relative inline-flex items-center px-4 py-2 border border-gray-300 text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50",
            start === 1 && " opacity-50 cursor-not-allowed"
          )}
          disabled={start === 1}
        >
          Previous
        </button>
        <button
          onClick={nextClickAction}
          className={classNames(
            "ml-3 relative inline-flex items-center px-4 py-2 border border-gray-300 text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50",
            end >= total && " opacity-50 cursor-not-allowed"
          )}
          disabled={end >= total}
        >
          Next
        </button>
      </div>
    </nav>
  );
}
