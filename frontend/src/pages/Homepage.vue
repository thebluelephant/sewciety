<template>
  <div class="homepage">
    <span class="homepage__header">
      <div
        class="homepage__header__headline"
        v-if="!$auth.isAuthenticated.value"
      >
        <img :src="require('../assets/image/logo-pink.png')" alt="logo" />
        <p class="text">
          <span class="headline--pink">Sewciety </span>,
          {{ $t("homepage.headline1") }}
          <span class="headline--green">{{ $t("homepage.headline2") }}</span>
          {{ $t("homepage.headline3") }} &#128578;
        </p>
      </div>

      <div
        class="homepage__header__title title--primary"
        v-if="$auth.isAuthenticated.value && username"
      >
        {{ $t("common.hello") }}
        <span class="username">{{ username }}</span
        >! &#128075;
      </div>
    </span>

    <span class="homepage__pattern-research">
      <PatternResearch />
    </span>

    <div class="homepage__quickaccess">
      <span class="title title--secondary">{{
        $t("homepage.quick-access")
      }}</span>
      <basic-button
        :title="$t('patternsubmissionpage.submit-pattern')"
        @click="redirectOnPatternSubmission"
        type="navigation"
      />
    </div>
  </div>
</template>

<script>
import PatternResearch from "../components/PatternResearch.vue";
import BasicButton from "../components/Basic-Button.vue";
import { userService } from "../services/user.service";
import router from "../router/router.js";

export default {
  name: "Homepage",
  components: {
    PatternResearch,
    BasicButton,
  },
  data() {
    return {
      file: "",
      username: null,
    };
  },
  mounted() {
    if (this.$auth.isAuthenticated.value) {
      userService
        .getCurrentUserUsername()
        .then((username) => (this.username = username));
    }
  },
  methods: {
    redirectOnPatternSubmission() {
      router.push({
        name: "PatternSubmissionPage",
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Homepage";
</style>
