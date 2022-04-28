import axios from "axios";
const apiUrl = "http://localhost:8081/api";
import { userService } from "./user.service";

export const apiCall = {
  createNewStepByStep: (patternId, lastStepDone, steps) => {
    const userId = userService.getCurrentUserId();
    const body = {
      patternId: parseFloat(patternId),
      authorId: userId,
      lastStepDone: lastStepDone,
    };

    // We call the step by step creation end point, which combine 2 BE actions :
    // The step by step creation, and the onProgress creation
    return axios.post(`${apiUrl}/stepbystep/create`, body).then((response) => {
      if (response.status === 200) {
        // Once the call has succeed, we call the steps submissions end point
        // with the stepByStepID returned by the /create call
        const stepByStepId = response.data;
        return apiCall.submitSteps(steps, stepByStepId);
      } else console.log("New steps : A problem happened", response);
    });
  },

  submitSteps: (steps, sbsId) => {
    const config = {
      headers: {
        "Content-Type": "multipart/form-data;charset=UTF-8",
      },
    };

    let formData = new FormData();

    for (let index = 0; index < steps.titles.length; index++) {
      formData.append(`sequences[]`, steps.sequences[index]);
      formData.append(`titles[]`, steps.titles[index]);
      formData.append("sbsId", sbsId);
      formData.append(`explanations[]`, steps.explanations[index]);
      formData.append(`images[]`, steps.images[index]);
    }

    return axios
      .post(`${apiUrl}/patternstep/newSteps`, formData, config)
      .then((response) => {
        if (response.status === 200) {
          return response.status;
        } else console.log("New steps : A problem happened", response);
      });
  },
};
