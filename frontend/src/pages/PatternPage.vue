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
    <div v-if="displayedComponent == 'Patron'" class="pattern">
      <img class="pattern__image" alt="pattern img" :src="pattern.imageUrl" />
      <p class="pattern__title title--primary">{{ pattern.name }}</p>
      <p class="pattern__description">{{ pattern.description }}</p>
    </div>
  </div>
</template>

<script>
import ChipMenu from "../components/ChipMenu.vue";
import { apiCall } from "../services/patterns-api";

export default {
  components: { ChipMenu },
  name: "PatternPage",
  data() {
    return {
      pattern: "",
      displayedComponent: "",
    };
  },
  methods: {
    fetchPatternData() {
      apiCall.findPatternById(this.$route.params.id).then((resp) => {
        if (resp) {
          this.pattern = resp;
        }
      });
    },
    setVisibleComponent(componentName) {
      this.displayedComponent = componentName;
    },
  },
  beforeMount() {
    this.fetchPatternData();
    this.displayedComponent = "Patron";
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Patternpage";
</style>
