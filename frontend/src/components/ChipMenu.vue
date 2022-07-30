<template>
  <div class="chip-menu">
    <!--  We can't encapsulate the input + label in a span (and avoid 2 v-for) because it will break the CSS parent/child relation 
    and push us to use JS to realize the tabs animation-->
    <input
      v-for="(chip, index) in statedChips"
      :key="chip"
      @click="onChipClick(index, chip)"
      type="radio"
      :id="`radio-${index}`"
      name="tabs"
    />
    <label
      v-for="(chip, index) in statedChips"
      :key="chip"
      :class="[chip.active ? 'tab tab--active' : 'tab']"
      :for="`radio-${index}`"
      >{{ chip.name }}</label
    >
    <span class="glider"></span>
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
