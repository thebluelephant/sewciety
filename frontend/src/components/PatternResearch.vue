<template>
  <div class="pattern-research">
    <span v-if="!minimized" class="title title--secondary"
      >Rechercher un patron</span
    >
    <div
      class="container"
      :class="{ 'container--minimized': minimized && !showMoreCriterias }"
    >
      <input
        class="container__search-input"
        placeholder="Titre d'un patron, marque, numéro..."
        type="text"
        v-model="research"
      />

      <select
        class="container__brand-select"
        name="brand-selector"
        v-model="brand"
        v-if="!minimized || (minimized && showMoreCriterias)"
      >
        <option value="" disabled>Select a brand</option>
        <option
          v-for="brand in brands"
          v-show="brand"
          :value="brand"
          :key="brand"
        >
          {{ brand }}
        </option>
      </select>

      <div
        class="container__buttons"
        :class="{ 'container__buttons--minimized': minimized }"
      >
        <p
          class="more-criterias"
          v-if="minimized || showMoreCriterias"
          @click="showMoreCriterias = !showMoreCriterias"
        >
          <span v-if="!showMoreCriterias">Plus</span>
          <span v-if="showMoreCriterias">Moins</span> de critères
        </p>
        <primary-button
          title="Rechercher"
          @click="onResearch"
          :mini="minimized"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { apiCall } from "../services/patterns-api";
import PrimaryButton from "../components/PrimaryButton.vue";
import router from "../router/router.js";

export default {
  components: { PrimaryButton },
  name: "PatternResearch",
  props: ["minimized"],
  component: {
    PrimaryButton,
  },
  data() {
    return {
      research: "",
      brand: "",
      brands: [],
      showMoreCriterias: false,
    };
  },
  methods: {
    onResearch() {
      //  The minimized config is only used in the research page so we doesn't need to update routing
      if (this.minimized) {
        this.$emit("research-pattern", this.research, this.brand);
      } else {
        router.push({
          name: "Research",
          query: { research: this.research, brand: this.brand },
        });
      }
    },
  },
  beforeMount() {
    apiCall.getBrands().then((resp) => {
      this.brands = resp;
    });
  },
};
</script>

<style lang="scss" scoped>
@import "../style/PatternResearch";
</style>
