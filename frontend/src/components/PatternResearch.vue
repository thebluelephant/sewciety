<template>
  <div class="pattern-research">
    <div class="container">
      <span class="container__header">
        <LoaderMini v-if="!brands.length" class="loader-mini" />
      </span>

      <span class="container__body">
        <input
          class="search-input"
          :placeholder="$t('patternresearchpage.research-placeholder')"
          type="text"
          v-model="research"
          :disabled="!brands.length"
        />
        <select
          class="brand-select"
          name="brand-selector"
          v-model="brand"
          v-if="brands.length > 0"
        >
          <option value="">{{ $t("patternresearchpage.select-brand") }}</option>
          <option
            v-for="brand in brands"
            v-show="brand"
            :value="brand"
            :key="brand"
          >
            {{ brand }}
          </option>
        </select>
      </span>

      <div class="container__buttons">
        <basic-button
          :title="$t('homepage.research')"
          @clicked="onResearch"
          type="navigation"
          :mini="true"
          :disable="!brands.length"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { apiCall } from "../services/patterns-api";
import BasicButton from "./Basic-Button.vue";
import LoaderMini from "../components/LoaderMini.vue";

export default {
  components: { BasicButton, LoaderMini },
  name: "PatternResearch",
  component: {
    BasicButton,
  },
  data() {
    return {
      research: "",
      brand: "",
      brands: [],
    };
  },
  beforeMount() {
    apiCall.getBrands().then((resp) => {
      this.brands = resp;
    });
  },
  methods: {
    onResearch() {
      this.$emit("research-pattern", this.research, this.brand);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/PatternResearch";
</style>
