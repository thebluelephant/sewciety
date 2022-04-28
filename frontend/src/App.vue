<template>
  <span>
    <div class="menu">
      <svg @click="toggleMenu">
        <use class="open-icon" xlink:href="#burgerMenu" />
      </svg>
      <BurgerMenu @hide-menu="toggleMenu" :showMenu="showMenu" />
    </div>
    <router-view />
  </span>
</template>

<script>
import BurgerMenu from "./components/BurgerMenu.vue";
import { userService } from "./services/user.service";

export default {
  name: "App",
  components: {
    BurgerMenu,
  },
  data() {
    return {
      showMenu: false,
    };
  },
  methods: {
    toggleMenu() {
      this.showMenu = !this.showMenu;
    },
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
  watch: {
    $route() {
      this.showMenu = false;
    },
  },
  mounted() {
    this.setLang();
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
  overflow: auto;

  .menu {
    height: 30px;
    display: flex;

    svg {
      height: 25px;
      width: 25px;
    }
    .open-icon {
      fill: $green;
    }
  }
}
</style>
