<template>
  <div class="homepage">
    <div class="title title--primary">{{ $t("common.hello") }} Marie !</div>
    <PatternResearch />
  </div>
</template>

<script>
import PatternResearch from "../components/PatternResearch.vue";

export default {
  name: "Homepage",
  components: {
    PatternResearch,
  },
  data() {
    return {
      file: "",
    };
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Homepage";
</style>
