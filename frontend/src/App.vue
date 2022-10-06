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

export default {
  name: "App",
  components: {
    Loader,
    BurgerMenu,
    Alert,
    BasicButton,
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
    this.setLang();
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
    setLang() {
      userService.getUserLang().then((userLangPreference) => {
        const i18n = this.$root.$i18n.locale;
        localStorage.setItem("lang", userLangPreference);
        if (i18n !== userLangPreference) {
          this.$root.$i18n.locale = userLangPreference;
        }
      });
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
@import "style/Global.scss";

@font-face {
  font-family: "QuickSand-regular";
  src: url("assets/fonts/Quicksand_Regular/QuicksandRegular.eot");
  src: url("assets/fonts/Quicksand_Regular/QuicksandRegular.eot?#iefix")
      format("embedded-opentype"),
    url("assets/fonts/Quicksand_Regular/QuicksandRegular.woff") format("woff"),
    url("assets/fonts/Quicksand_Regular/QuicksandRegular.ttf")
      format("truetype");
}
@font-face {
  font-family: "QuickSand-bold";
  src: url("assets/fonts/Quicksand_Bold/QuicksandBold.eot");
  src: url("assets/fonts/Quicksand_Bold/QuicksandBold.eot?#iefix")
      format("embedded-opentype"),
    url("assets/fonts/Quicksand_Bold/QuicksandBold.woff") format("woff"),
    url("assets/fonts/Quicksand_Bold/QuicksandBold.ttf") format("truetype");
}
@font-face {
  font-family: "QuickSand-light";
  src: url("assets/fonts/Quicksand_Light/QuicksandLight.eot");
  src: url("assets/fonts/Quicksand_Light/QuicksandLight.eot?#iefix")
      format("embedded-opentype"),
    url("assets/fonts/Quicksand_Light/QuicksandLight.woff") format("woff"),
    url("assets/fonts/Quicksand_Light/QuicksandLight.ttf") format("truetype");
}

$burgerMenuDimension: 40px;

html {
  background: #f7f7f8;
}

#app {
  font-family: "QuickSand-bold";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  position: absolute;
  margin: 0;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 15px;
  background: #f7f7f8;
  overflow: hidden;

  .loader {
    z-index: 1000;
  }
}

.header {
  display: flex;
  margin-bottom: 20px;
  .menu {
    height: $burgerMenuDimension;
    width: $burgerMenuDimension;
  }
  .logo {
    width: 100%;
    display: flex;
    justify-content: center;
    cursor: pointer;
    margin-left: $burgerMenuDimension; // Allows to really center the div

    @include respond-under(lg) {
      margin-right: 0;
    }
    @include respond-under(xs) {
      margin-left: 0;
    }
    img {
      height: 35px;
      @media (max-width: 400px) {
        height: 20px;
      }
    }
  }
  .signin-button {
    @include respond-under(xs) {
      display: none;
    }
  }
}

body {
  position: fixed;
  height: 100vh;
  width: 100vw;
  margin: 0;
}
</style>
