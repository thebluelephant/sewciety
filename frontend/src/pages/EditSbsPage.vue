<template>
  <div class="create-sbs-page">
    <popin
      :description="$t('stepbystep.delete-step')"
      :showPopin="showStepDeletionPopin"
      @onConfirm="deleteStep()"
      @onCancel="
        () => {
          showStepDeletionPopin = false;
          itemToDeleteIndex = null;
        }
      "
    />
    <popin
      :description="$t('stepbystep.delete-stepbystep')"
      :showPopin="showSbsDeletionPopin"
      @onConfirm="deleteStepByStep()"
      @onCancel="
        () => {
          showSbsDeletionPopin = false;
        }
      "
    />
    <div
      v-for="(step, index) in steps"
      :key="index"
      class="create-sbs-page__cards-container"
    >
      <step-card
        class="card"
        :initialValue="getValuesByIndex(index)"
        :index="index"
        @titleChange="editTitle(index, $event)"
        @imageChange="editImage(index, $event)"
        @explanationsChange="editExplanations(index, $event)"
        @isFieldValid="setFieldValidity(index, $event)"
      />
      <span
        @click="
          () => {
            itemToDeleteIndex = index;
            showStepDeletionPopin = true;
          }
        "
        class="delete-button"
      >
        <svg>
          <use class="open-icon" xlink:href="#close" />
        </svg>
      </span>
    </div>

    <div class="create-sbs-page__addStep-container">
      <basic-button
        :title="`${$t('createsbspage.add-step')}`"
        @click="addStep()"
        :disable="steps.length && steps.some((step) => !step.isValid)"
        type="action"
      />
    </div>
    <div class="create-sbs-page__submit-container">
      <basic-button
        class="save"
        :title="`${$t('createsbspage.save')}`"
        :disable="isSaveButtonDisable()"
        @click="save()"
        type="secondary-navigation"
      />
      <basic-button
        class="publish"
        :title="`${$t('createsbspage.publish')}`"
        :disable="isPublishButtonDisable()"
        @click="publish()"
        type="navigation"
      />
    </div>
  </div>
</template>

<script>
import StepCard from "../components/StepCard.vue";
import BasicButton from "../components/Basic-Button.vue";
import Popin from "../components/Popin.vue";
import { apiCall } from "../services/stepByStep-api";
var isEqual = require("lodash/isEqual");
var cloneDeep = require("lodash/cloneDeep");

export default {
  name: "EditSbsPage",
  components: { StepCard, BasicButton, Popin },
  data() {
    return {
      steps: [],
      existingSteps: [],
      newStepsToSave: [],
      showStepDeletionPopin: false, // Delete one step
      showSbsDeletionPopin: false, // Delete the entire step by step
      itemToDeleteIndex: null,
    };
  },
  mounted() {
    if (this.$route.params.sbsId) {
      this.fetchStepByStep();
    }
  },
  methods: {
    // EDITING
    addStep() {
      this.steps.push({
        image: null,
        title: "",
        explanations: "",
        isValid: false,
      });
    },
    editTitle(index, title) {
      this.steps[index].title = title;
    },
    editImage(index, image) {
      this.steps[index].image = image;
    },
    editExplanations(index, explanations) {
      this.steps[index].explanations = explanations;
    },
    updatedNewStepsToSave() {
      // We filter steps that are differents (or new) from the initial steps received from the back
      // To avoid uselessly modify value in DB whereas a step hasn't been modified.
      this.newStepsToSave = this.steps.filter((steps, index) => {
        return !isEqual(steps, this.existingSteps[index]);
      });
    },
    getValuesByIndex(index) {
      return {
        title: this.steps[index].title,
        explanations: this.steps[index].explanations,
        image: this.steps[index].image,
      };
    },
    save() {
      if (this.validateSteps()) {
        if (this.$route.params.sbsId) {
          this.saveSteps();
        } else {
          this.createNewStepByStep(true);
        }
      }
    },
    publish() {
      if (this.validateSteps()) {
        if (this.$route.params.sbsId) {
          apiCall
            .updateSbsProgress(this.$route.params.sbsId, false)
            .then(() => {
              this.saveSteps();
            });
        } else {
          this.createNewStepByStep(false);
        }
      }
    },

    // VALIDATING
    isSaveButtonDisable() {
      const isNewSbs = !this.$route.params.sbsId;
      const hasNoSteps = this.steps.length === 0;
      return (
        (isNewSbs && hasNoSteps) || this.steps.some((step) => !step.isValid)
      );
    },
    isPublishButtonDisable() {
      return (
        this.steps.length === 0 || this.steps.some((step) => !step.isValid)
      );
    },
    validateSteps() {
      const hasNoSteps = this.steps.length === 0;
      // If we are on a sbs edition, and the user deleted all the steps, then we should propose delete the sbs entirely
      if (hasNoSteps) {
        this.showSbsDeletionPopin = true;
        return false;
      } else if (this.isSaveButtonDisable() || this.isPublishButtonDisable()) {
        return false;
      } else return true;
    },
    setFieldValidity(index, isValid) {
      this.steps[index].isValid = isValid;
    },

    // DELETING
    deleteStep() {
      this.showStepDeletionPopin = false;
      const stepId = this.steps[this.itemToDeleteIndex].id;

      // If there's a stepId it means the step has already been saved in the DB so we need to delete it
      // thanks to the API. Else we just have to delete it localy.
      if (stepId) {
        apiCall.deleteStep(stepId).then((response) => {
          if (response.status === 200) {
            this.steps.splice(this.itemToDeleteIndex, 1);
          }
        });
      } else {
        this.steps.splice(this.itemToDeleteIndex, 1);
      }
      this.itemToDeleteIndex = null;
    },
    deleteStepByStep() {
      this.showSbsDeletionPopin = false;
      if (this.$route.params.sbsId) {
        apiCall.deleteStepByStep(this.$route.params.sbsId).then((response) => {
          if (response.status === 200) {
            this.$router.push({ path: `/pattern/${this.$route.params.id}` });
          }
        });
      } else this.$router.push({ path: `/pattern/${this.$route.params.id}` });
    },

    // FETCHING
    fetchStepByStep() {
      apiCall.getStepsBySbsId(this.$route.params.sbsId).then((response) => {
        if (response) {
          // We have to set those 2 variables equally because this.steps will be increment each time a new step is created
          // and existingSteps will stay. It'll allow to compare both variable on the submit method, to only send to the DB
          // the modified/new steps
          this.steps = response.map((step) => {
            return { ...step, isValid: true };
          });
          this.existingSteps = cloneDeep(this.steps);
        }
      });
    },
    createNewStepByStep(onProgress) {
      this.emitter.emit("displayLoader");
      apiCall
        .createNewStepByStep(this.$route.params.id, this.steps, onProgress)
        .then((response) => {
          if (response === 200) {
            this.$router.push({ path: `/pattern/${this.$route.params.id}` });
          }
        });
    },
    saveSteps() {
      this.emitter.emit("displayLoader");
      this.updatedNewStepsToSave();
      
      if (this.newStepsToSave.length > 0) {
        apiCall
          .submitSteps(this.newStepsToSave, this.$route.params.sbsId)
          .then((response) => {
            if (response === 200) {
              this.$router.push({ path: `/pattern/${this.$route.params.id}` });
            }
          });
      } else this.$router.push({ path: `/pattern/${this.$route.params.id}` });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/EditSbspage";
</style>
