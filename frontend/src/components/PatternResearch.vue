<template>
  <div class="pattern-research">
    <div class="container" :class="{ 'container--minimized': minimized }">
      <span class="container__header">
        <span v-if="!minimized" class="title title--secondary">{{
          $t("homepage.find-pattern")
        }}</span>
        <LoaderMini v-if="!brands.length && !minimized" class="loader-mini" />
      </span>

      <span
        class="container__body"
        :class="{ 'container__body--minimized': minimized }"
      >
        <input
          class="search-input"
          :class="{ 'search-input--minimized': minimized }"
          :placeholder="$t('homepage.research-placeholder')"
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
          <option value="">{{ $t("homepage.select-brand") }}</option>
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
          :mini="minimized"
          :disable="!brands.length"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { apiCall } from "../services/patterns-api";
import BasicButton from "./Basic-Button.vue";
import router from "../router/router.js";
import LoaderMini from "../components/LoaderMini.vue";

export default {
  components: { BasicButton, LoaderMini },
  name: "PatternResearch",
  props: ["minimized"],
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
      //  The minimized config is only used in the research page so we don't need to redirect user on research
      if (this.minimized) {
        this.$emit("research-pattern", this.research, this.brand);
      } else if (this.brand || this.research) {
        router.push({
          name: "Research",
          query: { research: this.research, brand: this.brand },
        });
      } else return;
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/PatternResearch";
</style>
