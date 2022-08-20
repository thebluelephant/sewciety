<template>
  <div class="pattern-card">
    <img class="pattern-card__image" alt="pattern img" :src="img"/>
    <basic-button
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
import BasicButton from "./Basic-Button.vue";
import router from "../router/router.js";

export default {
  components: { BasicButton },
  name: "PatternCard",
  props: ["title", "route", "img", "id"],
  methods: {
    onRedirect(){
      router.push({
        name: "Patternpage",
        params: { id: this.id },
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/PatternCard";
</style>
