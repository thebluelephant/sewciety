<template>
  <div class="sbs-page">
    <div
      v-for="(step, index) in steps.titles"
      :key="index"
      class="sbs-page__cards-container"
    >
      <step-card
        class="card"
        :initialValue="getInitialIndexValues(index)"
        :index="index"
        @titleChange="editTitle(index, $event)"
        @imageChange="editImage(index, $event)"
        @explanationsChange="editExplanations(index, $event)"
      />
      <span @click="deleteStep(index)" class="delete-button">
        <svg>
          <use class="open-icon" xlink:href="#close" />
        </svg>
      </span>
    </div>

    <div class="sbs-page__addStep-container">
      <primary-button :title="`${$t('stepbysteppage.add-step')}`" @click="addStep()" type="action" />
    </div>
    <div class="sbs-page__submit-container">
      <primary-button
        class="submit"
        :title="`${$t('stepbysteppage.publish')}`"
        @click="submit()"
        type="navigation"
      />
    </div>
  </div>
</template>

<script>
import StepCard from "../components/StepCard.vue";
import PrimaryButton from "../components/PrimaryButton.vue";
import { apiCall } from "../services/stepByStep-api";

export default {
  name: "StepByStep",
  components: { StepCard, PrimaryButton },
  data() {
    return {
      steps: {
        images: [],
        titles: [],
        explanations: [],
        sequences: [],
      },
      lastIndex: "",
    };
  },
  methods: {
    addStep() {
      this.steps.images.push("");
      this.steps.titles.push("");
      this.steps.explanations.push("");
      this.steps.sequences.push("");
      this.setSequence();
    },
    deleteStep(index) {
      const stepsKeys = Object.keys(this.steps);
      stepsKeys.forEach((key) => {
        const initialSteps = { ...this.steps };
        initialSteps[key].splice(index, 1);
        this.steps = initialSteps;
      });
    },
    getInitialIndexValues(index) {
      return {
        title: this.steps.titles[index],
        explanations: this.steps.explanations[index],
        //We don't set initial value for image to avoid any bug regarding the type of file and everything
        image: "",
      };
    },
    setSequence() {
      // If we add a step, the new index (and so, the sequence number) is automatically the last index of the array
      this.lastIndex = this.steps.titles.length - 1;
      this.steps.sequences[this.lastIndex] = this.lastIndex;
    },
    editTitle(index, title) {
      this.steps.titles[index] = title;
    },
    editImage(index, image) {
      this.steps.images[index] = image;
    },
    editExplanations(index, explanations) {
      this.steps.explanations[index] = explanations;
    },
    async submit() {
      const response = await apiCall.createNewStepByStep(
        this.$route.params.id,
        this.lastIndex,
        this.steps
      );
      if (response === 200) {
        this.$router.push({ path: `/pattern/${this.$route.params.id}` });
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/StepByStepPage";
</style>
