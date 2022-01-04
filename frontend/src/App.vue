<template>
  <span>
    <BurgerMenu />
    <router-view />
  </span>
</template>

<script>
import BurgerMenu from "./components/BurgerMenu.vue";
export default {
  name: "App",
  components: {
    BurgerMenu,
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
  font-family: QuickSand-book;
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
}
</style>
