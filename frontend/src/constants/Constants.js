export const InputValidationConst = {
  username: {
    minLength: 3,
    maxLength: 256,
  },
  password: {
    minLength: 3,
    maxLength: 256,
  },
  common: {
    minLength: 3,
    maxLength: 256,
  },
};

export const MessageType = {
  ERROR: "ERROR",
  SUCCESS: "SUCCESS",
  WARNING: "WARNING",
};
