<template>
  <div class="pattern-research">
    <span class="title title--secondary">Rechercher un patron</span>
    <div class="container">
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
      >
        <option value="" disabled hidden>Select a brand</option>
        <option
          v-for="brand in brands"
          v-show="brand"
          :value="brand"
          :key="brand"
        >
          {{ brand }}
        </option>
      </select>

      <div class="container__button">
        <primary-button title="Rechercher" route="" v-on:click="onResearch()" />
      </div>
    </div>
  </div>
</template>

<script>
import { apiCall } from "../services/patterns-api";
import PrimaryButton from "../components/PrimaryButton.vue";

export default {
  components: { PrimaryButton },
  name: "PatternResearch",
  component: {
    PrimaryButton,
  },
  data() {
    return {
      research: "",
      brand: "",
      brands: [],
    };
  },
  methods: {
    onResearch: function () {
      apiCall
        .searchPattern(this.research, this.brand)
        .then((resp) => console.log(resp));
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