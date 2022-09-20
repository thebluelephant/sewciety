<template>
  <div class="research-page">
    <span class="research-page__research">
      <PatternResearch
        :minimized="true"
        @research-pattern="onManualResearchPattern"
      />
      <basic-button
        id="basic-button__submit-pattern"
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
    <Pagination
      :totalPages="totalPages"
      :currentPage="currentIndexPage"
      @pageIndex-next="() => onIndexClicked(this.currentIndexPage + 1)"
      @pageIndex-previous="() => onIndexClicked(this.currentIndexPage - 1)"
    />
  </div>
</template>

<script>
import PatternCard from "../components/PatternCard.vue";
import PatternResearch from "../components/PatternResearch.vue";
import BasicButton from "../components/Basic-Button.vue";
import Pagination from "../components/Pagination.vue";
import router from "../router/router.js";
import { apiCall } from "../services/patterns-api";
import { useMeta } from "vue-meta";

export default {
  components: { PatternCard, PatternResearch, BasicButton, Pagination },
  name: "Research",
  setup() {
    useMeta({ title: "Sewciety - Rechercher un patron de couture", content : "Sewciety met à disposition un champ de recherche de patron de couture par marque, ou par titre." });
  },
  data() {
    return {
      patterns: null,
      totalPages: "",
      researchInputValue: null,
      researchBrandValue: null,
      currentIndexPage: 0,
    };
  },
  beforeMount() {
    if (this.$route.query.research || this.$route.query.brand) {
      this.emitter.emit("displayLoader");
      this.fetchPatterns();
    }
  },
  methods: {
    fetchPatterns(pageNumber) {
      const research = this.researchInputValue ?? this.$route.query.research;
      const brand = this.researchBrandValue ?? this.$route.query.brand;
      apiCall.findPattern(research, brand, pageNumber, 30).then((resp) => {
        if (resp) {
          this.patterns = resp.content;
          this.totalPages = resp.totalPages;
          this.emitter.emit("hideLoader");
        }
      });
    },
    onManualResearchPattern(research, brand) {
      this.researchInputValue = research;
      this.researchBrandValue = brand;
      this.fetchPatterns();
    },
    redirectOnPatternSubmission() {
      if (this.$auth.isAuthenticated.value) {
        router.push({
          name: "PatternSubmissionPage",
        });
      }
    },
    onIndexClicked(indexClicked) {
      this.currentIndexPage = indexClicked;
      this.fetchPatterns(indexClicked);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Researchpage";
</style>
