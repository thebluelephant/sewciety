<template>
  <div class="login">
    <div v-if="!$auth.loading.value">
      <primary-button
        v-if="!$auth.isAuthenticated.value"
        class="pattern-card__button"
        title="Connexion"
        @click="login"
      />
      <primary-button
        v-if="$auth.isAuthenticated.value"
        class="pattern-card__button"
        title="Deconnexion"
        @click="logout"
      />
    </div>
  </div>
</template>

<script>
import PrimaryButton from "../components/PrimaryButton.vue";

export default {
  components: { PrimaryButton },
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
