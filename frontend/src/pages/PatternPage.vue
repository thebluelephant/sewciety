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
      <primary-button
        class="create-sbs"
        :title="`${$t('stepbysteppage.create-sbs')}`"
        @click="redirectToSbsCreationPage()"
        type="action"
      />
    </div>
  </div>
</template>

<script>
import ChipMenu from "../components/ChipMenu.vue";
import { apiCall } from "../services/patterns-api";
import PrimaryButton from "../components/PrimaryButton.vue";

export default {
  components: { ChipMenu, PrimaryButton },
  name: "PatternPage",
  data() {
    return {
      pattern: "",
      displayedComponent: "",
      patternId: "",
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
    redirectToSbsCreationPage() {
      this.$router.push({ path: `/pattern/${this.patternId}/sbs/create` });
    },
  },
  beforeMount() {
    this.fetchPatternData();
    this.displayedComponent = "Patron";
    this.patternId = this.$route.params.id;
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Patternpage";
</style>
