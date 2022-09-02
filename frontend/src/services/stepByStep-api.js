import axios from "axios";
import { imageService } from "../services/image.service";
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

  submitSteps: async (steps, sbsId) => {
    const config = {
      headers: {
        "Content-Type": "multipart/form-data;charset=UTF-8",
      },
    };
    const formData = new FormData();
    const formattedSteps = await Promise.all(
      steps.map(async (step) => {
        let formatStep = {
          title: step.title,
          explanations: step.explanations,
          sbsId: sbsId,
          // If image didn't change, we don't need to format it to base64 because the value is already the image URL
          image:
            typeof step.image === "object"
              ? await imageService.fileToBase64(step.image)
              : step.image,
        };
        if (step.id) {
          formatStep.id = step.id;
        }
        return formatStep;
      })
    );

    // We have to always add an empty object in formattedSteps AND images to be sure to have an Array sent to the BE, specially if there's only 1
    // pattern step sent, FormData will send an uniq object that doesn't fit the expected array of string waited by SpringBoot
    formattedSteps.push({
      title: undefined,
      explanations: undefined,
      sbsId: undefined,
      image: undefined,
    });

    formattedSteps.forEach((formattedStep) => {
      formData.append("steps", JSON.stringify(formattedStep));
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
          return response.data;
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
