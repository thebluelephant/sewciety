<template>
  <div class="step-card">
    <div
      :class="[
        disableTitleEdition
          ? `step-card__header--disabled`
          : 'step-card__header',
      ]"
    >
      <p class="number">{{ formatedIndex }} -</p>
      <p v-if="disableTitleEdition" class="title--disabled ">
        {{ initialValue?.title }}
      </p>

      <input
        class="title input--neumorphic"
        type="text"
        :value="initialValue?.title"
        @change="onTitleChange($event)"
        v-if="!disableTitleEdition"
      />
    </div>
    <div class="step-card__body">
      <div class="image">
        <image-input
          v-if="!disableImageUpload"
          :index="index"
          :initialValue="initialValue.image"
          @imageChange="onImageChange($event)"
        />
        <img
          v-if="disableImageUpload && initialValue.image"
          :src="`data:image/png;base64,${initialValue?.image}`"
          alt=""
        />
      </div>

      <p v-if="disableExplanationsEdition" class="description--disabled">
        {{ initialValue?.explanations }}
      </p>

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
import ImageInput from "../components/ImageInput.vue";
export default {
  name: "StepCard",

  components: { ImageInput },
  props: [
    "index",
    "initialValue",
    "disableImageUpload",
    "disableExplanationsEdition",
    "disableTitleEdition",
  ],
  watch: {
    initialValue() {
      this.validateFields();
    },
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
    validateFields() {
      const hasTitle = this.initialValue.title.length > 0;
      const hasExplanations = this.initialValue.explanations.length > 0;
      const hasImage = this.initialValue.image !== null;

      if (hasTitle && hasExplanations && hasImage) {
        this.$emit("isFieldValid", true);
      } else {
        this.$emit("isFieldValid", false);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/StepCard";
</style>
