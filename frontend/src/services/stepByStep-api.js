import axios from "axios";
const apiUrl = process.env.VUE_APP_API_URL;
import { userService } from "./user.service";

export const apiCall = {
  getFileFromBase64(string64, fileName) {
    const trimmedString = string64.replace("dataimage/jpegbase64", "");
    const imageContent = atob(trimmedString);
    const buffer = new ArrayBuffer(imageContent.length);
    const view = new Uint8Array(buffer);

    for (let n = 0; n < imageContent.length; n++) {
      view[n] = imageContent.charCodeAt(n);
    }
    const type = "image/jpeg";
    const blob = new Blob([buffer], { type });
    return new File([blob], fileName, {
      lastModified: new Date().getTime(),
      type,
    });
  },

  // Create a new Step By Step entry in the DB + new OnProgress entry in the sbsOnProgress table + call the end point to create Steps entries in the DB too.
  createNewStepByStep: async (patternId, steps, onProgress) => {
    const userId = userService.getCurrentUserId();
    const username = await userService.getCurrentUserUsername();
    const body = {
      patternId: parseFloat(patternId),
      authorId: userId,
      authorUsername: username,
      onProgress: onProgress,
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

  deleteStepByStep: (sbsId) => {
    return axios
      .delete(`${apiUrl}/stepbystep/delete/${sbsId}`)
      .then((response) => {
        if (response.status === 200) {
          return response;
        } else console.log("deleteStepByStep: A problem happened", response);
      });
  },
  getStepByStepById: (patternId) => {
    const userId = userService.getCurrentUserId();
    return axios
      .post(`${apiUrl}/stepbystep/findAllSbs/${patternId}/${userId}`)
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
      let formatStep = {
        title: step.title,
        explanations: step.explanations,
        sbsId: sbsId,
      };
      if (step.id) {
        formatStep.id = step.id;
      }
      return formatStep;
    });

    // We have to always add an empty object in formattedSteps AND images to be sure to have an Array sent to the BE, specially if there's only 1
    // pattern step sent, FormData will send an uniq object that doesn't fit the expected array of string waited by SpringBoot
    formattedSteps.push({
      title: undefined,
      explanations: undefined,
      sbsId: undefined,
    });
    let images = steps.map((step) => step.image);
    images.push("");

    formattedSteps.forEach((formattedStep, index) => {
      formData.append("steps", JSON.stringify(formattedStep));

      // If the patternStep already exist and the image hasn't been updated once again
      // we have to format its initial FE value in base64 to re-send it to the DB
      if (typeof images[index] === "string") {
        formData.append(
          "images",
          apiCall.getFileFromBase64(images[index], "imageStep")
        );
      } else {
        formData.append("images", images[index]);
      }
    });

    return axios
      .post(`${apiUrl}/patternstep/editSteps`, formData, config)
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

  deleteStep: (sbsId) => {
    return axios.delete(`${apiUrl}/patternstep/${sbsId}`).then((response) => {
      return response;
    });
  },

  updateSbsProgress: (sbsId, progress) => {
    return axios
      .post(
        `${apiUrl}/progress/update/${sbsId}`,
        {},
        {
          params: {
            progress,
          },
        }
      )
      .then((response) => {
        return response;
      });
  },
};
