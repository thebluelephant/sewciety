import axios from "axios";
const apiUrl = "http://localhost:8081/api";

export const apiCall = {
  // Search pattern by input or brand
  searchPattern: function(input, brand) {
    return axios
      .get(`${apiUrl}/pattern`, {
        params: {
          ...(input ? { input: input } : {}),
          ...(brand ? { brand: brand } : {}),
        },
      })
      .then((response) => {
        if (response.status === 200) {
          return response.data;
        } else console.log("Search Pattern : A problem happened", response);
      });
  },

  searchPatternById : function(id) {
    return axios
      .get(`${apiUrl}/pattern/${id}`)
      .then((response) => {
        if (response.status === 200) {
          return response.data;
        } else console.log("Search Pattern by ID : A problem happened", response);
      });
  },

  // Get list of brands
  getBrands: function() {
    return axios.get(`${apiUrl}/pattern/brands`).then((response) => {
      if (response.status === 200) {
        return response.data;
      } else console.log("Get brands : A problem happened", response);
    });
  },
};
