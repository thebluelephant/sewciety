<template>
  <div class="step-card">
    <div class="step-card__header">
      <p class="number">{{ formatedIndex }} -</p>
      <input
        class="title input--neumorphic"
        type="text"
        :value="initialValue.title"
        @change="onTitleChange($event)"
      />
    </div>
    <div class="step-card__body">
      <div class="image">
        <input
          type="file"
          :value="initialValue.image"
          multiple
          @change="onImageChange($event)"
        />
      </div>
      <textarea
        class="description input--neumorphic"
        cols="30"
        rows="10"
        :value="initialValue.explanations"
        @change="onExplanationsChange($event)"
      ></textarea>
    </div>
  </div>
</template>

<script>
export default {
  name: "StepCard",
  props: ["index", "initialValue"],
  data() {
    return {};
  },
  mounted() {
    console.log(this.initialValue);
  },
  computed: {
    formatedIndex() {
      return this.index + 1;
    },
  },

  methods: {
    onTitleChange(e) {
      this.$emit("titleChange", e.target.value);
    },
    onImageChange(e) {
      this.$emit("imageChange", e.target.files[0]);
    },
    onExplanationsChange(e) {
      this.$emit("explanationsChange", e.target.value);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/StepCard";
</style>
