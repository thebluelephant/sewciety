import axios from "axios";
const apiUrl = process.env.VUE_APP_API_URL;

export const apiCall = {
  // Find pattern by input or brand
  findPattern: (input, brand, pageNumber, nbrByPage) => {
    return axios
      .get(`${apiUrl}/pattern`, {
        params: {
          ...(input ? { input: input } : {}),
          ...(brand ? { brand: brand } : {}),
          page : pageNumber,
          size : nbrByPage
        },
      })
      .then((response) => {
        if (response.status === 200) {
          return response.data;
        } else console.log("Find Pattern : A problem happened", response);
      });
  },

  findPatternById: (id) => {
    return axios.get(`${apiUrl}/pattern/${id}`).then((response) => {
      if (response.status === 200) {
        return response;
      } else console.log("Find Pattern by ID : A problem happened", response);
    });
  },

  submitNewPattern: (pattern) => {
    return axios.post(`${apiUrl}/pattern/new`, pattern).then((response) => {
      if (response.status === 200) {
        return response.data;
      } else console.log("Submit a new pattern : A problem happened", response);
    });
  },

  // Get list of brands
  getBrands: () => {
    return axios.get(`${apiUrl}/pattern/brands`).then((response) => {
      if (response.status === 200) {
        return response.data;
      } else console.log("Get brands : A problem happened", response);
    });
  },
};
