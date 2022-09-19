<template>
  <div class="pattern-page" v-if="pattern">
    <ChipMenu
      :chips="[
        `${$t('patternpage.pattern')}`,
        `${$t('patternpage.step-by-step')}`,
      ]"
      @chip-clicked="setVisibleComponent"
    />
    <div
      v-if="displayedComponent == `${$t('patternpage.pattern')}`"
      class="pattern"
    >
      <img
        class="pattern__image"
        alt="pattern img"
        :src="pattern.image"
        @error="setAltImage"
      />
      <p class="pattern__title title--primary">{{ pattern.name }}</p>
      <p class="pattern__description">{{ pattern.description }}</p>
    </div>
    <div
      v-if="displayedComponent == `${$t('patternpage.step-by-step')}`"
      class="step-by-step"
    >
      <step-by-step-container :patternId="patternId" />
    </div>
  </div>
</template>

<script>
import ChipMenu from "../components/ChipMenu.vue";
import { apiCall } from "../services/patterns-api";
import StepByStepContainer from "../components/StepByStepContainer.vue";
import { imageService } from "../services/image.service";
import { useMeta } from "vue-meta";

export default {
  components: { ChipMenu, StepByStepContainer },
  name: "PatternPage",
  setup() {
    useMeta({ title: "Page Patron de couture" });
  },
  data() {
    return {
      pattern: "",
      displayedComponent: "",
      patternId: "",
    };
  },
  beforeMount() {
    this.patternId = this.$route.params.id;
    this.emitter.emit("displayLoader");
    this.fetchPatternData();
    this.displayedComponent = "Patron";
  },
  methods: {
    fetchPatternData() {
      apiCall.findPatternById(this.patternId).then((resp) => {
        if (resp) {
          this.pattern = resp.data;
          this.emitter.emit("hideLoader");
        }
      });
    },
    setVisibleComponent(componentName) {
      this.displayedComponent = componentName;
    },
    setAltImage(event) {
      return imageService.setAlternateImage(event);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Patternpage";
</style>
