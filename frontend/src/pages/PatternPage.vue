<template>
  <div class="pattern-page" v-if="pattern">
    <ChipMenu
      :chips="[
        `${$t('patternpage.pattern')}`,
        `${$t('patternpage.step-by-step')}`,
        `${$t('patternpage.achievements')}`,
      ]"
      @chip-clicked="setVisibleComponent"
    />
    <div
      v-if="displayedComponent == `${$t('patternpage.pattern')}`"
      class="pattern"
    >
      <img class="pattern__image" alt="pattern img" :src="pattern.imageUrl" />
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

export default {
  components: { ChipMenu, StepByStepContainer },
  name: "PatternPage",
  data() {
    return {
      pattern: "",
      displayedComponent: "",
      patternId: "",
    };
  },
  beforeMount() {
    this.emitter.emit("displayLoader");
    this.fetchPatternData();
    this.displayedComponent = "Patron";
    this.patternId = this.$route.params.id;
  },
  methods: {
    fetchPatternData() {
      apiCall.findPatternById(this.$route.params.id).then((resp) => {
        if (resp) {
          this.pattern = resp;
          this.emitter.emit("hideLoader");
        }
      });
    },
    setVisibleComponent(componentName) {
      this.displayedComponent = componentName;
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Patternpage";
</style>
