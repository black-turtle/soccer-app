export function getStartPaginationNo(page, limit) {
  return (page - 1) * limit + 1;
}

export function getEndPaginationNo(page, limit) {
  return (page - 1) * limit + limit;
}

export function formatToCurrency(value) {
  return new Intl.NumberFormat("en-US", {
    style: "currency",
    currency: "USD",
  }).format(value);
}

export function applyTextSnakeCaseFilter(value) {
  value = "" + value; // convert to string
  value = value.replace("_", " "); // for snake case
  return value;
}

export function applyTextCamelCaseFilter(value) {
  value = "" + value; // convert to string
  value = value.replace(/([A-Z])/g, " $1"); // for camel case
  return value;
}

export function applyBooleanFilter(value) {
  return value ? "YES" : "NO";
}

export function applyFilter(value, filter) {
  switch (filter) {
    case "text":
      return applyTextSnakeCaseFilter(value);
    case "boolean":
      return applyBooleanFilter(value);
    case "currency":
      return formatToCurrency(value);
    default:
      console.error("Invalid filter: " + filter);
  }
}

export function shouldDisplayField(displayFieldList, key) {
  if (!displayFieldList) {
    return true;
  }
  return key in displayFieldList;
}

export const playerDisplayFieldsWithType = {
  firstName: "text",
  lastName: "text",
  country: "text",
  age: "text",
  value: "currency",
  type: "text",
  canTransfer: "boolean",
};

export const transferDisplayFieldsWithType = {
  firstName: "text",
  lastName: "text",
  country: "text",
  age: "text",
  price: "currency",
  type: "text",
  owner: "text",
};
