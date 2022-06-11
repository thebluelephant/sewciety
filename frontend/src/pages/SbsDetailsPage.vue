<template>
  <div class="sbs-details-page">
    <span class="step" v-for="(step, index) of stepByStep" :key="step.id">
      <step-card
        :disableExplanationsEdition="true"
        :disableTitleEdition="true"
        :disableImageUpload="true"
        :index="index"
        :initialValue="step"
      />
    </span>
  </div>
</template>

<script>
import { apiCall } from "../services/stepByStep-api";
import StepCard from "../components/StepCard.vue";

export default {
  name: "SbsDetailsPage",
  components: { StepCard },
  data() {
    return {
      stepByStep: null,
    };
  },
  mounted() {
    this.fetchStepByStep();
  },
  methods: {
    fetchStepByStep() {
      apiCall.getStepsBySbsId(this.$route.params.sbsId).then((response) => {
        if (response) {
          this.stepByStep = response;
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/SbsDetailspage";
</style>
