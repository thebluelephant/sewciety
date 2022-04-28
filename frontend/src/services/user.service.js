import { apiCall } from "./user-api";

export const userService = {
  // Return User ID with the "auth0|" value in it. Usually used to deal with authentication process
  getCurrentAuthUserId: () => {
    const token = localStorage.getItem("token");
    return userService.decodeToken(token).sub;
  },

  // Return user ID without the "auth0|"
  getCurrentUserId: () => {
    const authId = userService.getCurrentAuthUserId();
    return authId.substring(6);
  },
  getCurrentUserUsername: async () => {
    const currentUserData = await userService.getCurrentUserData();
    if (currentUserData) {
      return currentUserData.username;
    }
  },
  getCurrentUserData: async () => {
    let authUserId = userService.getCurrentAuthUserId();
    const accessToken = await apiCall.getAccessToken();

    if (authUserId && accessToken) {
      return apiCall
        .getUserDataById(authUserId, accessToken)
        .then((response) => {
          return response;
        });
    }
  },
  getUserLang: async () => {
    const currentUserData = await userService.getCurrentUserData();
    if (currentUserData) {
      return currentUserData.user_metadata.lang;
    }
  },
  decodeToken: function(token) {
    return JSON.parse(atob(token.split(".")[1]));
  },
};
