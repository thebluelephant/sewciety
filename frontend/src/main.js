import { createApp } from "vue";
import App from "./App.vue";
import router from "./router/router";

import authConfig from "../auth_config.json";
import { setupAuth } from "./auth/auth";
import i18n from './i18n'

let app = createApp(App).use(i18n).use(router);

function callbackRedirect(appState) {
  router.push(appState && appState.targetUrl ? appState.targetUrl : "/");
}

setupAuth(authConfig, callbackRedirect).then((auth) => {
  app.use(auth).mount("#app");
});
