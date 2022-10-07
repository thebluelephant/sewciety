<template>
  <span>
    <metainfo>
      <template v-slot:title="{ content }">{{
        content ? `Sewciety - ${content}` : `Sewciety`
      }}</template>
    </metainfo>
    <Loader v-if="showLoader" class="loader" />
    <Alert />
    <div class="header">
      <div class="menu">
        <BurgerMenu />
      </div>
      <div class="logo">
        <img
          @click="this.$router.push('/')"
          :src="require('./assets/image/logo-horizontal-pink.png')"
          alt="logo"
        />
      </div>
      <span class="utils">
        <Bonus v-if="$auth.isAuthenticated.value" />
        <basic-button
          class="signin-button"
          :title="
            $auth.isAuthenticated.value
              ? $t('burgermenu.logout')
              : $t('common.signin')
          "
          @clicked="onSignIn"
          :type="
            $auth.isAuthenticated.value ? 'secondary-navigation' : 'navigation'
          "
        />
      </span>
    </div>
    <router-view />
  </span>
</template>

<script>
import BurgerMenu from "./components/BurgerMenu.vue";
import Loader from "./components/Loader.vue";
import Alert from "./components/Alert.vue";
import { userService } from "./services/user.service";
import { useMeta } from "vue-meta";
import BasicButton from "./components/Basic-Button.vue";
import Bonus from "./components/Bonus.vue";

export default {
  name: "App",
  components: {
    Loader,
    BurgerMenu,
    Alert,
    BasicButton,
    Bonus,
  },
  setup() {
    useMeta({
      title: "Sewciety App",
      htmlAttrs: { lang: "en", amp: true },
    });
  },
  data() {
    return {
      showLoader: false,
    };
  },
  mounted() {
    this.getUserMetadata();
    this.emitter.on("displayLoader", () => {
      this.showLoader = true;
    });
    this.emitter.on("hideLoader", () => {
      this.showLoader = false;
    });
  },
  watch: {
    $route() {
      this.showMenu = false;
    },
  },
  methods: {
    getUserMetadata() {
      userService.getUserMetadata().then((userMetadata) => {
        if (userMetadata.lang) {
          this.setLang(userMetadata.lang);
        }
        if (userMetadata.bonus !== null) {
          this.setBonus(userMetadata.bonus);
        }
      });
    },
    setLang(lang) {
      const i18n = this.$root.$i18n.locale;
      localStorage.setItem("lang", lang);
      if (i18n !== lang) {
        this.$root.$i18n.locale = lang;
      }
    },
    setBonus(bonus) {
      localStorage.setItem("bonus", bonus);
    },
    onSignIn() {
      if (this.$auth.isAuthenticated.value) {
        this.$auth.logout({
          returnTo: window.location.origin,
        });
      } else {
        this.$auth.loginWithRedirect();
      }
    },
  },
};
</script>

<style lang="scss">
@import "./style/App.scss";

</style>
