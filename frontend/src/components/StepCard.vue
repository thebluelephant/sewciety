<template>
  <div class="step-card">
    <form ref="form-stepcard" @submit.prevent="onSendEmail">
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
          @input="onTitleChange($event)"
          v-if="!disableTitleEdition"
        />
      </div>
      <div class="step-card__body" :class="disableImageUpload ? 'step-card__body--disabled' : ''">
        <div class="image">
          <image-input
            v-if="!disableImageUpload"
            :index="index"
            :initialValue="initialValue.image"
            @imageChange="onImageChange($event)"
          />
          <img
            v-if="disableImageUpload && initialValue.image"
            :src="initialValue?.image"
            alt="step by step image"
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
          @input="onExplanationsChange($event)"
        ></textarea>
      </div>
    </form>
  </div>
</template>

<script>
import ImageInput from "../components/ImageInput.vue";
import _debounce from "lodash/debounce";

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
  computed: {
    formatedIndex() {
      return this.index + 1;
    },
  },
  methods: {
    onTitleChange(e) {
      this.$emit("titleChange", e.target.value);
      this.validateFields();
    },
    onImageChange(image) {
      this.$emit("imageChange", image);
      this.validateFields();
    },
    onExplanationsChange(e) {
      this.$emit("explanationsChange", e.target.value);
      this.validateFields();
    },

    validateFields: _debounce(function() {
      const hasTitle = this.initialValue.title.length > 0;
      const hasExplanations = this.initialValue.explanations.length > 0;
      const hasImage = this.initialValue.image !== null;

      if (hasTitle && hasExplanations && hasImage) {
        this.$emit("isFieldValid", true);
      } else {
        this.$emit("isFieldValid", false);
      }
    }, 900),
  },
};
</script>

<style lang="scss" scoped>
@import "../style/StepCard";
</style>
