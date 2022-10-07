import { apiCall } from "./user-api";

export const userService = {
  // Return User ID with the "auth0|" value in it. Usually used to deal with authentication process
  getCurrentAuthUserId: () => {
    const token = localStorage.getItem("token");
    if (token) {
      return userService.decodeToken(token).sub;
    }
  },

  // Return user ID without the "auth0|"
  getCurrentUserId: () => {
    const authId = userService.getCurrentAuthUserId();
    if (authId) {
      return authId.substring(6);
    }
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

  decodeToken: function(token) {
    return JSON.parse(atob(token.split(".")[1]));
  },

  getUserBonus: async () => {
    const currentUserData = await userService.getCurrentUserData();
    if (currentUserData) {
      return currentUserData.user_metadata.bonus;
    }
  },

  getUserMetadata: async () => {
    const currentUserData = await userService.getCurrentUserData();
    if (currentUserData) {
      return currentUserData.user_metadata;
    }
  },
  
  incrementUserBonusOfOne: async () => {
    const authUserId = await userService.getCurrentAuthUserId();
    const userBonus = await userService.getUserBonus();

    if (userBonus !== null) {
      return apiCall
        .updateUserBonus(authUserId, userBonus + 1)
        .then((response) => {
          return response;
        });
    }
  },
};
