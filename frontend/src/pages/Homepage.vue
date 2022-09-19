<template>
  <div class="homepage">
    <span class="homepage__header">
      <span class="text">
        <p class="text__slogan">
          {{ $t("homepage.slogan-1") }}
          <span class="text__slogan--highlight">{{
            $t("homepage.slogan-2")
          }}</span>
          {{ $t("homepage.slogan-3") }}
        </p>
        <p class="text__description">
          {{ $t("homepage.description") }}
        </p>
      </span>
      <span class="image" />
    </span>

    <span class="homepage__pattern-research">
      <PatternResearch />
    </span>
  </div>
</template>

<script>
import PatternResearch from "../components/PatternResearch.vue";
import router from "../router/router.js";
import { useMeta } from "vue-meta";

export default {
  name: "Homepage",
  setup() {
    useMeta({ title: "Sewciety page d'accueil" });
  },
  components: {
    PatternResearch,
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
