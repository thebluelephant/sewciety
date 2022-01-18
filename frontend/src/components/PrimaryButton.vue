<template>
  <div class="primary-button">
    <a :href="route">
      <span class="title">{{ title }}</span>
    </a>
  </div>
</template>

<script>
export default {
  name: "PrimaryButton",
  props: ["title", "route"],
};
</script>

<style lang="scss" scoped>
@import "../style/PrimaryButton";
</style>