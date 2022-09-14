<template>
  <div class="pattern-card">
    <img
      class="pattern-card__image"
      alt="pattern img"
      :src="img"
      @error="setAltImage"
    />
    <basic-button
      class="pattern-card__button"
      :mini="true"
      :route="route"
      type="navigation"
      @clicked="onRedirect"
    />
    <span class="pattern-card__title">{{ title }}</span>
  </div>
</template>

<script>
import BasicButton from "./Basic-Button.vue";
import router from "../router/router.js";
import { imageService } from "../services/image.service";

export default {
  components: { BasicButton },
  name: "PatternCard",
  props: ["title", "route", "img", "id"],
  methods: {
    onRedirect() {
      router.push({
        name: "Patternpage",
        params: { id: this.id },
      });
    },
    setAltImage(event) {
      return imageService.setAlternateImage(event);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/PatternCard";
</style>
