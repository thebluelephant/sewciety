<template>
  <div :class="['primary-button', { 'primary-button--mini': mini }]">
    <a :href="route">
      <span v-if="!mini" class="title">{{ title }}</span>
      <svg v-if="mini">
        <use class="icon" xlink:href="#chevron-right" />
      </svg>
    </a>
  </div>
</template>

<script>
export default {
  name: "PrimaryButton",
  props: ["title", "route", "mini"],
};
</script>

<style lang="scss" scoped>
@import "../style/PrimaryButton";
</style>
