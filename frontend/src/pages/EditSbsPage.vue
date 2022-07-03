<template>
  <div class="create-sbs-page">
    <popin
      :description="$t('stepbystep.delete-step')"
      :showPopin="showDeletePopin"
      @onConfirm="deleteStep()"
      @onCancel="
        () => {
          showDeletePopin = false;
          itemToDeleteIndex = null;
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
        :initialValue="getInitialIndexValues(index)"
        :index="index"
        @titleChange="editTitle(index, $event)"
        @imageChange="editImage(index, $event)"
        @explanationsChange="editExplanations(index, $event)"
      />
      <span
        @click="
          () => {
            itemToDeleteIndex = index;
            showDeletePopin = true;
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
        type="action"
      />
    </div>
    <div class="create-sbs-page__submit-container">
      <basic-button
        class="save"
        :title="`${$t('createsbspage.save')}`"
        @click="save()"
        type="secondary-navigation"
      />
      <basic-button
        class="publish"
        :title="`${$t('createsbspage.publish')}`"
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
      savedSteps: [],
      showDeletePopin: false,
      itemToDeleteIndex: null,
    };
  },
  mounted() {
    if (this.$route.params.sbsId) {
      this.fetchStepByStep();
    }
  },
  methods: {
    addStep() {
      this.steps.push({
        image: "",
        title: "",
        explanations: "",
      });
    },
    deleteStep() {
      this.showDeletePopin = false;
      const stepId = this.steps[this.itemToDeleteIndex].id;

      if (stepId) {
        apiCall.deleteStep(stepId).then((response) => {
          if (response.status === 200) {
            this.steps.splice(this.itemToDeleteIndex, 1);
            this.itemToDeleteIndex = null;
          }
        });
      } else {
        this.steps.splice(this.itemToDeleteIndex, 1);
        this.itemToDeleteIndex = null;
      }
    },
    getInitialIndexValues(index) {
      return {
        title: this.steps[index].title,
        explanations: this.steps[index].explanations,
        image: this.steps[index].image,
      };
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
    fetchStepByStep() {
      apiCall.getStepsBySbsId(this.$route.params.sbsId).then((response) => {
        if (response) {
          this.steps = response.slice();
          this.savedSteps = cloneDeep(response);
        }
      });
    },
    save() {
      if (this.$route.params.sbsId) {
        this.saveSteps();
      } else {
        this.createNewStepByStep(true);
      }
    },
    publish() {
      if (this.$route.params.sbsId) {
        apiCall.updateSbsProgress(this.$route.params.sbsId, false).then(() => {
          this.saveSteps();
        });
      } else {
        this.createNewStepByStep(false);
      }
    },
    saveSteps() {
      this.emitter.emit("displayLoader");

      // We filter steps that are differents (or new) from the initial steps received from the back
      // To avoid uselessly modify value in DB whereas a step hasn't been modified.
      const stepsToSave = this.steps.filter((steps, index) => {
        return !isEqual(steps, this.savedSteps[index]);
      });

      if (stepsToSave.length > 0) {
        apiCall
          .submitSteps(stepsToSave, this.$route.params.sbsId)
          .then((response) => {
            if (response === 200) {
              this.$router.push({ path: `/pattern/${this.$route.params.id}` });
            }
          });
      } else this.$router.push({ path: `/pattern/${this.$route.params.id}` });
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
  },
};
</script>

<style lang="scss" scoped>
@import "../style/EditSbspage";
</style>
