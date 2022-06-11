<template>
  <div class="stepbystep-container">
    <p v-if="hasSavedSbs">Mes pas à pas sauvegardés</p>
    <div class="sbs-list" v-for="sbs of stepBySteps.saved" :key="sbs.id">
      <div class="sbs-list__card">
        <span class="card__header">
          <p class="username">{{ sbs.authorUsername }}</p>
          <p class="date">{{ formatedDate(sbs.date) }}</p>
        </span>

        <basic-button
          class="sbs-list__redirection"
          @click="redirectToSbsCreationPage(sbs.id)"
          type="navigation"
          :mini="true"
        />
      </div>
    </div>
    <span
      class="separator"
      v-if="hasSavedSbs && hasPublishedSbs"
    ></span>
    <div class="sbs-list" v-for="sbs of stepBySteps.published" :key="sbs.id">
      <div class="sbs-list__card">
        <span class="card__header">
          <p class="username">{{ sbs.authorUsername }}</p>
          <p class="date">{{ formatedDate(sbs.date) }}</p>
        </span>

        <basic-button
          class="sbs-list__redirection"
          @click="redirectToSbsById(sbs.id)"
          type="navigation"
          :mini="true"
        />
      </div>
    </div>
    <basic-button
      class="create-sbs"
      :class="[
        `create-sbs`,
        hasSavedSbs || hasPublishedSbs
          ? ''
          : `create-sbs--centered`,
      ]"
      :title="`${$t('createsbspage.create-sbs')}`"
      @click="redirectToSbsCreationPage()"
      type="action"
    />
  </div>
</template>

<script>
import BasicButton from "./Basic-Button.vue";
import { apiCall } from "../services/stepByStep-api";
import moment from "moment";

export default {
  components: { BasicButton },
  name: "StepByStepContainer",
  props: ["patternId"],
  data() {
    return {
      stepBySteps: [],
      hasSavedSbs : false,
      hasPublishedSbs : false
    };
  },
  methods: {
    formatedDate(stepByStepDate) {
      return moment(stepByStepDate).format("DD-MM-YYYY");
    },
    redirectToSbsCreationPage(sbsId) {
      this.$router.push({
        name: "EditSbsPage",
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
          this.hasSavedSbs = resp.saved.length ;
          this.hasPublishedSbs = resp.published.length ;
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
