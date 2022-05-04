<template>
  <div class="create-sbs-page">
    <div
      v-for="(step, index) in steps"
      :key="index"
      class="create-sbs-page__cards-container"
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

    <div class="create-sbs-page__addStep-container">
      <primary-button
        :title="`${$t('createsbspage.add-step')}`"
        @click="addStep()"
        type="action"
      />
    </div>
    <div class="create-sbs-page__submit-container">
      <primary-button
        class="submit"
        :title="`${$t('createsbspage.publish')}`"
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
  name: "CreateSbsPage",
  components: { StepCard, PrimaryButton },
  data() {
    return {
      steps: [],
    };
  },
  methods: {
    addStep() {
      this.steps.push({
        image: "",
        title: "",
        explanation: "",
      });
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
        title: this.steps[index].title,
        explanations: this.steps[index].explanation,
        //We don't set initial value for image to avoid any bug regarding the type of file and everything
        image: "",
      };
    },
    editTitle(index, title) {
      this.steps[index].title = title;
    },
    editImage(index, image) {
      this.steps[index].image = image;
    },
    editExplanations(index, explanations) {
      this.steps[index].explanation = explanations;
    },
    async submit() {
      const response = await apiCall.createNewStepByStep(
        this.$route.params.id,
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
@import "../style/CreateSbspage";
</style>
