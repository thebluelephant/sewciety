<template>
  <div class="pattern-card">
    <img class="pattern-card__image" alt="pattern img" :src="img" />
    <primary-button
      class="pattern-card__button"
      :mini="true"
      :route="route"
      type="navigation"
      @click="onRedirect"
    />
    <span class="pattern-card__title">{{ title }}</span>
  </div>
</template>

<script>
import PrimaryButton from "./PrimaryButton.vue";
import router from "../router/router.js";

export default {
  components: { PrimaryButton },
  name: "PatternCard",
  props: ["title", "route", "img", "id"],
  data() {
    return {};
  },
  methods: {
    onRedirect: function() {
      router.push({
        name: "Patternpage",
        params: { id: this.id },
      });
    },
  },
  beforeMount() {},
};
</script>

<style lang="scss" scoped>
@import "../style/PatternCard";
</style>
