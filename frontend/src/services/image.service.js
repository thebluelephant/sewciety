export const imageService = {
  // Check if the string is a Base64 or not
  isBase64: (stringToTest) => {
    const base64regex = /^([0-9a-zA-Z+/]{4})*(([0-9a-zA-Z+/]{2}==)|([0-9a-zA-Z+/]{3}=))?$/;
    return base64regex.test(stringToTest);
  },

  fileToBase64: (file) => {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () =>
        resolve(
          reader.result
            .toString()
            .substr(reader.result.toString().indexOf(",") + 1)
        );
      reader.onerror = (error) => reject(error);
    });
  },
};
