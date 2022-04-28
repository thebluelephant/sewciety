<template>
  <div class="stepbystep-container">
    <div class="sbs-list" v-for="sbs of stepBySteps" :key="sbs.id">
      <div class="sbs-list__card">
        <span class="card__header">
          <p class="username">{{ sbs.authorUsername }}</p>
          <p class="date">{{ formatedDate(sbs.date) }}</p>
        </span>

        <primary-button
          class="sbs-list__redirection"
          @click="redirectToSbsById(sbs.id)"
          type="navigation"
          :mini="true"
        />
      </div>
    </div>
    <primary-button
      class="create-sbs"
      :title="`${$t('stepbysteppage.create-sbs')}`"
      @click="redirectToSbsCreationPage()"
      type="action"
    />
  </div>
</template>

<script>
import PrimaryButton from "./PrimaryButton.vue";
import { apiCall } from "../services/stepByStep-api";
import moment from "moment";

export default {
  components: { PrimaryButton },
  name: "StepByStepContainer",
  props: ["patternId"],
  data() {
    return {
      stepBySteps: [],
    };
  },
  methods: {
    formatedDate(stepByStepDate) {
      return moment(stepByStepDate).format("DD-MM-YYYY");
    },
    redirectToSbsCreationPage() {
      this.$router.push(`/pattern/${this.patternId}/sbs/create`);
    },
    redirectToSbsById(sbsId) {
      this.$router.push(`/sbs/${sbsId}`);
    },
    fetchSbsList() {
      apiCall.getAllMinimizedStepByStepById(this.patternId).then((resp) => {
        if (resp) {
          this.stepBySteps = resp;
          console.log(this.stepBySteps);
        }
      });
    },
  },
  mounted() {
    this.fetchSbsList();
  },
};
</script>

<style lang="scss" scoped>
@import "../style/StepByStepContainer";
</style>
