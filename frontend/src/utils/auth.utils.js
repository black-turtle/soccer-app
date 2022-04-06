export function authHeader() {
  const token = localStorage.getItem(TOKEN_KEY);

  if (token) {
    return {
      Authorization: `Bearer ${token}`,
    };
  } else {
    return {};
  }
}

export const TOKEN_KEY = "JWT_TOKEN";
export const USER_NAME = "USER_NAME";
