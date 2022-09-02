<template>
  <div class="homepage">
    <span class="homepage__header">
      <div class="title title--primary" v-if="username">
        {{ $t("common.hello") }}
        <span class="title__username">{{ username }}</span
        >!
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
    userService
      .getCurrentUserUsername()
      .then((username) => (this.username = username));
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
