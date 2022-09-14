<template>
  <div class="research-page">
    <span class="research-page__research">
      <PatternResearch :minimized="true" @research-pattern="fetchPatterns" />
      <basic-button
        :title="$t('patternsubmissionpage.submit-pattern')"
        @clicked="redirectOnPatternSubmission"
        :disable="!$auth.isAuthenticated.value"
        :toolTip="$t('common.tooltip-signin')"
        type="navigation"
      />
    </span>
    <span class="research-page__cards">
      <p v-if="this.patterns !== null && !this.patterns.length">
        {{ $t("patternresearchpage.no-pattern-available") }}
      </p>
      <PatternCard
        v-for="pattern in patterns"
        :key="pattern.id"
        :title="pattern.name"
        :img="pattern.image"
        :id="pattern.id"
        class="card"
      />
    </span>
  </div>
</template>

<script>
import PatternCard from "../components/PatternCard.vue";
import PatternResearch from "../components/PatternResearch.vue";
import BasicButton from "../components/Basic-Button.vue";
import router from "../router/router.js";
import { apiCall } from "../services/patterns-api";

export default {
  components: { PatternCard, PatternResearch, BasicButton },
  name: "Research",
  data() {
    return {
      patterns: null,
    };
  },
  beforeMount() {
    if (this.$route.query.research || this.$route.query.brand) {
      this.emitter.emit("displayLoader");
      this.fetchPatterns();
    }
  },
  methods: {
    fetchPatterns(researchTerm, brandTerm) {
      const research = researchTerm ?? this.$route.query.research;
      const brand = brandTerm ?? this.$route.query.brand;

      apiCall.findPattern(research, brand).then((resp) => {
        if (resp) {
          
          this.patterns = resp;
          this.emitter.emit("hideLoader");
        }
      });
    },
    redirectOnPatternSubmission() {
      if (this.$auth.isAuthenticated.value) {
        router.push({
          name: "PatternSubmissionPage",
        });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Researchpage";
</style>
