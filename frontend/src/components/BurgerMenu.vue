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
              <span>{{$t('burgermenu.homepage')}}</span>
            </span>
          </router-link>
          <router-link class="container__routerlink" to="/research" exact>
            <span class="routerlink__box">
              <svg>
                <use class="icon" xlink:href="#search" />
              </svg>
              <span>{{$t('burgermenu.research')}}</span>
            </span>
          </router-link>
          <router-link class="container__routerlink" to="/inventory" exact>
            <span class="routerlink__box">
              <svg>
                <use class="icon" xlink:href="#inventory" />
              </svg>
              <span>{{$t('burgermenu.inventory')}}</span>
            </span>
          </router-link>
          <router-link class="container__routerlink" to="/tools" exact>
            <span class="routerlink__box">
              <svg>
                <use class="icon" xlink:href="#tool" />
              </svg>
              <span>{{$t('burgermenu.tools')}}</span>
            </span>
          </router-link>
          <span v-if="$auth.isAuthenticated.value" class="container__routerlink" @click="logout">
            <span class="routerlink__box">
              <svg>
                <use class="icon" xlink:href="#logout" />
              </svg>
              <span>{{$t('burgermenu.logout')}}</span>
            </span>
          </span>
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
    logout() {
      this.$auth.logout({
        returnTo: window.location.origin,
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/BurgerMenu";
</style>
