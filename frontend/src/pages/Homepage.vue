<template>
  <div class="homepage">
    <div class="title title--primary">{{ $t("common.hello") }} Marie !</div>
    <PatternResearch />
    <QuickAccess />
  </div>
</template>

<script>
import PatternResearch from "../components/PatternResearch.vue";
import QuickAccess from "../components/QuickAccess.vue";

export default {
  name: "Homepage",
  components: {
    QuickAccess,
    PatternResearch,
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Homepage";
</style>
