import {
  applyFilter,
  applyTextCamelCaseFilter,
  shouldDisplayField,
} from "../../utils/table.utils";

function createTableHeader(tableInfo, actionList, displayFields) {
  const tableKeys = Object.keys(tableInfo[0]);
  return (
    <>
      {tableKeys
        .filter((key) => shouldDisplayField(displayFields, key))
        .map((key) => (
          <th
            key={key}
            scope="col"
            className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
          >
            {applyTextCamelCaseFilter(key)}
            {/*{key}*/}
          </th>
        ))}
      {actionList.map((action) => (
        <th key={action.name} scope="col" className="relative px-6 py-3">
          <span className="sr-only">{action.name}</span>
        </th>
      ))}
    </>
  );
}

function createTableBody(tableInfo, actionList, displayFields) {
  const tableKeys = Object.keys(tableInfo[0]);
  return (
    <>
      {tableInfo.map((row, rowIdx) => (
        <tr
          key={rowIdx}
          className={rowIdx % 2 === 0 ? "bg-white" : "bg-gray-50"}
        >
          {tableKeys
            .filter((key) => shouldDisplayField(displayFields, key))
            .map((colKey, colIdx) => (
              <td
                key={`${rowIdx}-${colIdx}`}
                className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"
              >
                {applyFilter(row[colKey], displayFields[colKey])}
              </td>
            ))}

          {actionList.map((action) => (
            <td
              key={`${rowIdx}-${action.name}`}
              className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium"
            >
              <button
                className="text-indigo-600 hover:text-indigo-900 disabled:opacity-70 disabled:cursor-not-allowed disabled:text-gray-600 hover-focus-animation"
                onClick={() => {
                  action.task && action.task(row);
                }}
                disabled={
                  action.disableCondition && action.disableCondition(row)
                }
              >
                {action.name}
              </button>
            </td>
          ))}
        </tr>
      ))}
    </>
  );
}

function renderTable(data, actionList, displayFields) {
  return (
    <div className="flex flex-col">
      <div className="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
        <div className="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
          <div className="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg">
            <table className="min-w-full divide-y divide-gray-200">
              <thead className="bg-gray-50">
                <tr>{createTableHeader(data, actionList, displayFields)}</tr>
              </thead>
              <tbody>{createTableBody(data, actionList, displayFields)}</tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
}

export default function Table({ data, actionList, displayFields }) {
  return (
    <>
      {data && data.length
        ? renderTable(data, actionList, displayFields)
        : "Invalid data passed to table"}
    </>
  );
}
