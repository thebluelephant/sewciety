import emailjs from "@emailjs/browser";

export const emailService = {
  // Send email with emailJs
  sendEmail: (form) => {
   return emailjs
      .sendForm(process.env.VUE_APP_EMAILJS_SERVICEID, process.env.VUE_APP_EMAILJS_TEMPLATEID, form, process.env.VUE_APP_EMAILJS_PUBLICKEY)
      .then(
        (result) => {
          return result
        },
        (error) => {
          return error
        }
      );
  },
};
