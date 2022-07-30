<template>
  <content>
    <input id="hamburger" class="hamburger" type="checkbox" />
    <label class="hamburger" for="hamburger">
      <i></i>
    </label>
    <section class="drawer-list">
      <ul>
        <li>
          <router-link class="routerlink" to="/" exact>
            <svg>
              <use class="icon" xlink:href="#home" />
            </svg>
            <a>{{ $t("burgermenu.homepage") }}</a>
          </router-link>
        </li>

        <li>
          <router-link class="routerlink" to="/research" exact>
            <svg>
              <use class="icon" xlink:href="#search" />
            </svg>
            <a>{{ $t("burgermenu.research") }}</a>
          </router-link>
        </li>

        <li>
          <router-link class="routerlink" to="/inventory" exact>
            <svg>
              <use class="icon" xlink:href="#inventory" />
            </svg>
            <a>{{ $t("burgermenu.inventory") }}</a>
          </router-link>
        </li>

        <li>
          <router-link class="routerlink" to="/tools" exact>
            <svg>
              <use class="icon" xlink:href="#tool" />
            </svg>
            <a>{{ $t("burgermenu.tools") }}</a>
          </router-link>
        </li>

        <span
          v-if="$auth.isAuthenticated.value"
          class="container__routerlink"
          @click="logout"
        >
          <li>
            <svg>
              <use class="icon" xlink:href="#logout" />
            </svg>
            <a>{{ $t("burgermenu.logout") }}</a>
          </li>
        </span>
      </ul>
    </section>
  </content>
</template>

<script>
export default {
  name: "NewBurgerMenu",
  watch: {
    $route() {
      const burgerMenu = document.getElementById("hamburger");
      if (burgerMenu.checked) {
        burgerMenu.checked = false;
      }
    },
  },
  methods: {
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
