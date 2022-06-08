<template>
  <div class="login">
    <div v-if="!$auth.loading.value">
      <basic-button
        v-if="!$auth.isAuthenticated.value"
        class="pattern-card__button"
        title="Connexion"
        @click="login"
        type="navigation"
      />
      <basic-button
        v-if="$auth.isAuthenticated.value"
        class="pattern-card__button"
        title="Deconnexion"
        @click="logout"
        type="navigation"
      />
    </div>
  </div>
</template>

<script>
import BasicButton from '../components/Basic-Button.vue';

export default {
  components: { BasicButton },
  name: "Login",
  methods: {
    login() {
      this.$auth.loginWithRedirect();
    },
    // Log the user out
    logout() {
      this.$auth.logout({
        returnTo: window.location.origin,
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Loginpage.scss";
</style>
