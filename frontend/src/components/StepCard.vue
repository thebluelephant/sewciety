<template>
  <div class="step-card">
    <div
      :class="[disableTitleEdition ? `step-card__header--disabled` : 'step-card__header']"
    >
      <p class="number">{{ formatedIndex }} -</p>
      <p v-if="disableTitleEdition" class="title--disabled ">{{ initialValue.title }}</p>
      <input
        class="title input--neumorphic"
        type="text"
        :value="initialValue.title"
        @change="onTitleChange($event)"
        v-if="!disableTitleEdition"
      />
    </div>
    <div class="step-card__body">
      <div class="image">
        <input
          v-if="!disableImageUpload"
          type="file"
          multiple
          @change="onImageChange($event)"
        />
        <img v-if="disableImageUpload || initialValue.image" :src="`data:image/png;base64,${initialValue.image}`" alt="" />
      </div>

      <p v-if="disableExplanationsEdition" class="description--disabled">{{ initialValue.explanations }}</p>

      <textarea
        v-if="!disableExplanationsEdition"
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
  props: ["index", "initialValue", "disableImageUpload", "disableExplanationsEdition", "disableTitleEdition",  "onProgress"],
  data() {
    return {};
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
