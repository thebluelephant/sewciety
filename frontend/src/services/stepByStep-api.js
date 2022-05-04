import axios from "axios";
const apiUrl = "http://localhost:8081/api";
import { userService } from "./user.service";

export const apiCall = {
  createNewStepByStep: async (patternId, steps) => {
    const userId = userService.getCurrentUserId();
    const username = await userService.getCurrentUserUsername();
    const body = {
      patternId: parseFloat(patternId),
      authorId: userId,
      authorUsername: username,
    };

    // We call the step by step creation end point, which combine 2 BE actions :
    // The step by step creation, and the onProgress creation
    return axios.post(`${apiUrl}/stepbystep/create`, body).then((response) => {
      if (response.status === 200) {
        // Once the call has succeed, we call the steps submissions end point
        // with the stepByStepID returned by the /create call
        const stepByStepId = response.data;
        return apiCall.submitSteps(steps, stepByStepId);
      } else console.log("createNewStepByStep : A problem happened", response);
    });
  },

  getStepByStepById: (patternId) => {
    return axios
      .post(`${apiUrl}/stepbystep/findAllSbs/${patternId}`)
      .then((response) => {
        if (response.status === 200) {
          return response.data;
        } else console.log("getStepByStepById: A problem happened", response);
      });
  },

  submitSteps: (steps, sbsId) => {
    const config = {
      headers: {
        "Content-Type": "multipart/form-data;charset=UTF-8",
      },
    };
    const formData = new FormData();

    const formattedSteps = steps.map((step) => {
      return {
        title: step.title,
        explanations: step.explanation,
        sbsId: sbsId,
      };
    });
    const images = steps.map((step) => step.image);

    formattedSteps.forEach((formattedStep, index) => {
      formData.append("steps", JSON.stringify(formattedStep));
      formData.append("images", images[index]);
    });

    return axios
      .post(`${apiUrl}/patternstep/newSteps`, formData, config)
      .then((response) => {
        if (response.status === 200) {
          return response.status;
        } else console.log("submitSteps : A problem happened", response);
      });
  },

  getStepsBySbsId: (sbsId) => {
    return axios
      .post(`${apiUrl}/patternstep/findAllSteps/${sbsId}`)
      .then((response) => {
        if (response.status === 200) {
          return response.data.steps.map((step) => {
            let stepValues = { ...step };
            stepValues.image = response.data.images.find(
              (image) => image.stepId === step.id
            ).image;
            return stepValues;
          });
        } else console.log("getStepsBySbsId: A problem happened", response);
      });
  },
};
