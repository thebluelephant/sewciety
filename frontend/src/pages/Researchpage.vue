<template>
  <div class="research-page">
    <span class="research-page__research">
      <PatternResearch :minimized="true" @research-pattern="fetchPatterns" />
    </span>
    <span class="research-page__cards">
      <PatternCard
        v-for="pattern in patterns"
        :key="pattern.id"
        :title="pattern.name"
        :img="pattern.imageUrl"
        :id="pattern.id"
        class="card"
      />
    </span>
    <p v-if="!this.patterns.length">Aucun patron disponible</p>
  </div>
</template>

<script>
import PatternCard from "../components/PatternCard.vue";
import PatternResearch from "../components/PatternResearch.vue";
import { apiCall } from "../services/patterns-api";

export default {
  components: { PatternCard, PatternResearch },
  name: "Research",
  data() {
    return {
      patterns: [],
    };
  },
  beforeMount() {
    this.emitter.emit("displayLoader");
    this.fetchPatterns();
  },
  methods: {
    fetchPatterns(researchTerm, brandTerm) {
      const research = researchTerm ?? this.$route.query.research;
      const brand = brandTerm ?? this.$route.query.brand;

      apiCall.findPattern(research, brand).then((resp) => {
        if (resp.length) {
          this.patterns = resp;
          this.emitter.emit("hideLoader");
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Researchpage";
</style>
