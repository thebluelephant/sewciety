<template>
  <div class="pattern-research">
    <input
      class="pattern-research__search-input"
      :placeholder="$t('homepage.find-pattern')"
      type="text"
      v-model="research"
    />

    <basic-button
      :title="$t('homepage.research')"
      @clicked="onResearch"
      type="navigation"
    />
  </div>
</template>

<script>
import { apiCall } from "../services/patterns-api";
import BasicButton from "./Basic-Button.vue";
import router from "../router/router.js";

export default {
  components: { BasicButton },
  name: "HomePatternResearch",
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
      if (this.research) {
        router.push({
          name: "Research",
          query: { research: this.research },
        });
      } else return;
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/HomePatternResearch";
</style>
