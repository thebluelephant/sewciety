<template>
  <div
    :class="[
      'basic-button',
      `basic-button-${type}`,
      { 'basic-button--mini': mini },
    ]"
  >
    <a :href="route">
      <span v-if="!mini" class="title">{{ title }}</span>
      <svg v-if="mini">
        <use class="icon" :xlink:href="icon ?? '#chevron-right'" />
      </svg>
    </a>
  </div>
</template>

<script>
export default {
  name: "BasicButton",
  props: ["title", "route", "mini", "type", "icon"],
};
</script>

<style lang="scss" scoped>
@import "../style/BasicButton";
</style>
