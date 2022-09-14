<template>
  <div
    :class="[
      'basic-button',
      `basic-button-${type}`,
      { 'basic-button--mini': mini },
      { 'basic-button--disable': disable },
    ]"
    @mouseenter="manageTooltip(true)"
    @mouseleave="manageTooltip(false)"
    @click="disable ? null : this.$emit('clicked')"
  >
    <p class="tooltip" v-if="toolTip && showTooltip">{{ toolTip }}</p>
    <div class="container">
      <p v-if="!mini" class="title">{{ title }}</p>
      <svg v-if="mini">
        <use class="icon" :xlink:href="icon ?? '#chevron-right'" />
      </svg>
    </div>
  </div>
</template>

<script>
export default {
  name: "BasicButton",
  props: ["title", "mini", "type", "icon", "disable", "toolTip"],
  data() {
    return {
      showTooltip: false,
    };
  },
  methods: {
    manageTooltip(value) {
      if (this.disable) {
        if (value) {
          this.showTooltip = value;
        } else {
          setTimeout(() => {
            this.showTooltip = false;
          }, 1000);
        }
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/BasicButton";
</style>
