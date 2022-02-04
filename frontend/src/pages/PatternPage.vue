<template>
  <div class="pattern-page" v-if="pattern">
    <ChipMenu
      :chips="['Patron', 'Pas à pas', 'Réalisation']"
      @chip-clicked="setComponentVisibility"
    />
    <div v-if="componentDisplay == 'Patron'"  class="pattern">
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
      pattern: '',
      componentDisplay: "",
    };
  },
  methods: {
    fetchPatternData() {
      apiCall.searchPatternById(this.$route.params.id).then((resp) => {
        if (resp) {
          this.pattern = resp;
        }
      });
    },
    setComponentVisibility(componentName) {
      this.componentDisplay = componentName;
    },
  },
  beforeMount() {
    this.fetchPatternData();
    this.componentDisplay = "Patron";
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Patternpage";
</style>
