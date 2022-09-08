<template>
  <span>
    <Loader v-if="showLoader" class="loader" />
    <Alert />
    <div class="header">
      <div class="menu">
        <BurgerMenu />
      </div>
      <div class="logo">
        <img
          :src="require('./assets/image/logo-horizontal-pink.png')"
          alt="logo"
        />
      </div>
    </div>
    <router-view />
  </span>
</template>

<script>
import BurgerMenu from "./components/BurgerMenu.vue";
import Loader from "./components/Loader.vue";
import Alert from "./components/Alert.vue";
import { userService } from "./services/user.service";

export default {
  name: "App",
  components: {
    Loader,
    BurgerMenu,
    Alert,
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
  },
};
</script>

<style lang="scss">
@import "style/Global.scss";

@font-face {
  font-family: "QuickSand-book";
  src: url("assets/fonts/Quicksand_Book.otf") format("opentype");
}
@font-face {
  font-family: "QuickSand-bold";
  src: url("assets/fonts/Quicksand_Bold.otf") format("opentype");
}
@font-face {
  font-family: "QuickSand-light";
  src: url("assets/fonts/Quicksand_Light.otf") format("opentype");
}
@font-face {
  font-family: "QuickSand-dash";
  src: url("assets/fonts/Quicksand_Dash.otf") format("opentype");
}

$burgerMenuDimension: 40px;

html {
  background: #efefef;
}

#app {
  font-family: QuickSand-bold;
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
  background: #efefef;
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
    justify-content: end;
    img {
      height: 50px;
      @media (max-width: 700px) {
        height: 40px;
      }
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
