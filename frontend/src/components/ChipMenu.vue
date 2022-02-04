<template>
  <div class="chip-menu">
    <p
      :class="[`chip`, chip.active ? `chip--active` : '']"
      v-for="(chip, index) in statedChips"
      :key="chip"
      @click="onChipClick(index, chip)"
    >
      {{ chip.name }}
    </p>
  </div>
</template>

<script>
export default {
  name: "ChipMenu",
  props: ["chips"],
  components: {},
  data() {
    return {
      statedChips: [],
    };
  },
  beforeMount() {
    this.statedChips = this.chips.map((chip, index) => {
      return { name: chip, active: index === 0 };
    });
  },
  methods: {
    onChipClick(index, chip) {
      this.$emit("chip-clicked", chip.name);
      this.statedChips.forEach(
        (chip, chipIndex) => (chip.active = chipIndex === index)
      );
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/ChipMenu";
</style>
