<template>
  <div class="bonus">
    <span
      class="bonus__chip"
      @mouseenter="manageToolTip(true)"
      @mouseleave="manageToolTip(false)"
    >
      <svg viewBox="0 0 500 500">
        <path
          id="curve"
          d="M 86 154 c 4 -6.1 65.8 -132.6 160 -136 c 108 -5 153 144 150.2 137.6"
        />
        <text width="500">
          <textPath xlink:href="#curve">
            Bonus
          </textPath>
        </text>
      </svg>
      <p class="count" v-if="bonus">{{ bonus }}</p>
    </span>
    <p v-if="showTooltip" class="bonus__tooltip">
      {{ $t("bonus.tooltip") }}
    </p>
  </div>
</template>

<script>
export default {
  name: "Bonus",
  data() {
    return {
      bonus: false,
      showTooltip: false,
    };
  },
  mounted() {
    this.getBonus();
  },
  methods: {
    getBonus() {
      this.bonus = localStorage.getItem("bonus");
    },
    manageToolTip(value) {
      if (value) {
        this.showTooltip = value;
      } else {
        setTimeout(() => {
          this.showTooltip = false;
        }, 1000);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Bonus";
</style>
