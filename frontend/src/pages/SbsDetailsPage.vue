<template>
  <div class="sbs-details-page">
    <span class="steps">
      <span class="step" v-for="(step, index) of stepByStep" :key="step.id">
        <step-card
          :disableExplanationsEdition="true"
          :disableTitleEdition="true"
          :disableImageUpload="true"
          :index="index"
          :initialValue="step"
        />
      </span>
    </span>
  </div>
</template>

<script>
import { apiCall } from "../services/stepByStep-api";
import StepCard from "../components/StepCard.vue";
import { useMeta } from "vue-meta";

export default {
  name: "SbsDetailsPage",
  components: { StepCard },
  setup() {
    useMeta({ title: "Sewciety - Détails d'un tutoriel de couture", content : "Accédez au tutoriel de ce patron de couture écrit par un internaute l'ayant déjà réalisé." });
  },
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
