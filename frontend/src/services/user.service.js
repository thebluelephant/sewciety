import { apiCall } from "./user-api";

export const userService = {
  getCurrentUserId: () => {
    const token = localStorage.getItem("token");
    return userService.decodeToken(token).sub;
  },
  getCurrentUserData: async () => {
    let userId = userService.getCurrentUserId();
    const accessToken = await apiCall.getAccessToken();

    if (userId && accessToken) {
      return apiCall.getUserDataById(userId, accessToken).then((response) => {
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
