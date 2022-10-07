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
            <p>{{ $t("burgermenu.homepage") }}</p>
          </router-link>
        </li>

        <li>
          <router-link class="routerlink" to="/research" exact>
            <svg>
              <use class="icon" xlink:href="#search" />
            </svg>
            <p>{{ $t("burgermenu.research") }}</p>
          </router-link>
        </li>
        <li>
          <router-link class="routerlink" to="/pattern/new" exact>
            <svg>
              <use class="icon" xlink:href="#add-pattern" />
            </svg>
            <p>{{ $t("patternsubmissionpage.submit-pattern") }}</p>
          </router-link>
        </li>
        <li>
          <router-link class="routerlink" to="/contact" exact>
            <svg>
              <use class="icon" xlink:href="#send-message" />
            </svg>
            <p>{{ $t("burgermenu.contact") }}</p>
          </router-link>
        </li>
        <!--
        <li>
          <router-link class="routerlink" to="/inventory" exact>
            <svg>
              <use class="icon" xlink:href="#inventory" />
            </svg>
            <p>{{ $t("burgermenu.inventory") }}</p>
          </router-link>
        </li>

        <li>
          <router-link class="routerlink" to="/tools" exact>
            <svg>
              <use class="icon" xlink:href="#tool" />
            </svg>
            <p>{{ $t("burgermenu.tools") }}</p>
          </router-link>
        </li>
        -->
        <span
          v-if="$auth.isAuthenticated.value"
          class="container__routerlink"
          @click="logout"
        >
          <li>
            <svg>
              <use class="icon" xlink:href="#logout" />
            </svg>
            <p>{{ $t("burgermenu.logout") }}</p>
          </li>
        </span>
        <span
          v-if="!$auth.isAuthenticated.value"
          class="container__routerlink"
          @click="login"
        >
          <li>
            <svg>
              <use class="icon" xlink:href="#signin" />
            </svg>
            <span class=" sign-in">
              <p>{{ $t("common.signin") }}</p>
            </span>
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
    login() {
      this.$auth.loginWithRedirect();
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/BurgerMenu";
</style>
