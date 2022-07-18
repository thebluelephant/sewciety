const Dotenv = require("dotenv-webpack");

module.exports = {
  devServer: {
    open: process.platform === "darwin",
    host: "0.0.0.0",
    port: 8080,
    https: true,
    hotOnly: false,
  },

  configureWebpack: {
    plugins: [new Dotenv()],
  },

  pluginOptions: {
    i18n: {
      locale: "fr",
      fallbackLocale: "fr",
      localeDir: "i18n",
      enableLegacy: true,
      runtimeOnly: false,
      compositionOnly: true,
      fullInstall: true,
    },
  },
};
