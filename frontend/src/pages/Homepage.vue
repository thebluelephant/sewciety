<template>
  <div class="homepage">
    <div class="title title--primary">{{ $t("common.hello") }} Marie !</div>
    <PatternResearch />
    <QuickAccess />
    <input type="file" multiple @change="selectFile($event)" />
  </div>
</template>

<script>
import PatternResearch from "../components/PatternResearch.vue";
import QuickAccess from "../components/QuickAccess.vue";
import { apiCall } from "../services/patterns-api";

export default {
  name: "Homepage",
  components: {
    QuickAccess,
    PatternResearch,
  },
  data() {
    return {
      file: "",
    };
  },
  methods: {
    selectFile(event) {
      this.file = event.target.files[0];
      console.log(this.file);
      this.callApi(this.file)
    },

    callApi(image) {
      apiCall.createStep(image);
    },
  }
};
</script>

<style lang="scss" scoped>
@import "../style/Homepage";
</style>
