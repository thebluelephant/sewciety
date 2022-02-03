<template>
  <div class="burger-menu" v-if="showMenu">
    <transition name="slide-fade">
      <div class="container" v-if="showMenu">
        <div class="container__close-button">
          <svg @click="hideMenu()">
            <use class="icon" xlink:href="#close" />
          </svg>
        </div>
        <ul>
          <router-link class="container__routerlink" to="/" exact>
            <span class="routerlink__box">
              <svg>
                <use class="icon" xlink:href="#home" />
              </svg>
              <span>Accueil</span>
            </span>
          </router-link>
          <router-link class="container__routerlink" to="/research" exact>
            <span class="routerlink__box">
              <svg>
                <use class="icon" xlink:href="#search" />
              </svg>
              <span>Recherche</span>
            </span></router-link
          >
          <router-link class="container__routerlink" to="/inventory" exact>
            <span class="routerlink__box">
              <svg>
                <use class="icon" xlink:href="#inventory" />
              </svg>
              <span>Inventaire</span>
            </span></router-link
          >
          <router-link class="container__routerlink" to="/tools" exact>
            <span class="routerlink__box">
              <svg>
                <use class="icon" xlink:href="#tool" />
              </svg>
              <span>Outils</span>
            </span></router-link
          >
        </ul>
      </div>
    </transition>
  </div>
</template>

<script>
export default {
  name: "BurgerMenu",
  props: ["showMenu"],
  components: {},
  data() {
    return {
      show: false,
    };
  },
  methods: {
    hideMenu() {
      this.$emit("hide-menu");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/BurgerMenu";
</style>
