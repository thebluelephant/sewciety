import axios from "axios";
const apiUrl = "https://dev-bfwkqoak.us.auth0.com/api/v2";

export const apiCall = {
  // Get access token used to get user data
  getAccessToken: async () => {
    const body = {
      client_id: process.env.VUE_APP_AUTH_CLIENT_ID,
      client_secret: process.env.VUE_APP_AUTH_CLIENT_SECRET,
      audience: process.env.VUE_APP_AUTH_AUDIENCE,
      grant_type: "client_credentials",
    };

    const token = await axios
      .post("https://dev-bfwkqoak.us.auth0.com/oauth/token", body)
      .then((response) => {
        if (response.status === 200) {
          return response.data.access_token;
        } else console.log("Get access token : A problem happened", response);
      });

    return token;
  },

  // Get user authentication info
  getUserInfo: (accessToken) => {
    const config = {
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
        Authorization: "Bearer " + accessToken,
      },
    };

    return axios.get(`${apiUrl}/userInfo`, config).then((response) => {
      if (response.status === 200) {
        return response.data;
      } else
        console.log("CurrentUserAuthProfile : A problem happened", response);
    });
  },

  // Get user data based on its ID and an access token
  getUserDataById: (userId, accessToken) => {
    const config = {
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
        Authorization: "Bearer " + accessToken,
      },
    };
    return axios.get(`${apiUrl}/users/${userId}`, config).then((response) => {
      if (response.status === 200) {
        return response.data;
      } else console.log("User info: A problem happened", response);
    });
  },

  // Update user metadata "bonus" in Auth0 with a new value
  updateUserBonus: async (userId, newBonus) => {
    const accessToken = await apiCall.getAccessToken();
    const data = { user_metadata: { bonus: newBonus } };
    const config = {
      headers: {
        "Content-Type": "application/json;charset=UTF-8",
        Authorization: "Bearer " + accessToken,
      },
    };
    return axios
      .patch(`${apiUrl}/users/${userId}`, data, config)
      .then((response) => {
        if (response.status === 200) {
          return response.data;
        } else console.log("User bonus: A problem happened", response);
      });
  },
};
