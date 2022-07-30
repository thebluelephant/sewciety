<template>
  <span>
    <Loader v-if="showLoader" class="loader" />
    <div class="menu">
      <BurgerMenu />
    </div>
    <router-view />
  </span>
</template>

<script>
import BurgerMenu from "./components/BurgerMenu.vue";
import Loader from "./components/Loader.vue";
import { userService } from "./services/user.service";

export default {
  name: "App",
  components: {
    Loader,
    BurgerMenu,
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

.menu {
  height: 40px;
  width: 40px;
}

body {
  position: fixed;
  height: 100vh;
  width: 100vw;
  margin: 0;
}
</style>
