<template>
  <div class="stepbystep-container">
    <div class="sbs-list" v-for="sbs of stepBySteps.saved" :key="sbs.id">
      <div class="sbs-list__card">
        <span class="card__header">
          <p class="username">{{ sbs.authorUsername }}</p>
          <p class="date">{{ formatedDate(sbs.date) }}</p>
        </span>

        <primary-button
          class="sbs-list__redirection"
          @click="redirectToSbsCreationPage(sbs.id)"
          type="navigation"
          :mini="true"
        />
      </div>
    </div>
    <span class="separator"></span>
    <div class="sbs-list" v-for="sbs of stepBySteps.published" :key="sbs.id">
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
      :title="`${$t('createsbspage.create-sbs')}`"
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
    redirectToSbsCreationPage(sbsId) {
      this.$router.push({
        name: "CreateSbsPage",
        params: { patternId: this.patternId, sbsId: sbsId },
      });
    },
    redirectToSbsById(sbsId) {
      this.$router.push({ name: "SbsDetailsPage", params: { sbsId: sbsId } });
    },
    fetchSbsList() {
      apiCall.getStepByStepById(this.patternId).then((resp) => {
        if (resp) {
          this.stepBySteps = resp;
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
