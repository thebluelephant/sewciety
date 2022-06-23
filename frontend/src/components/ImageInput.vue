<template>
  <span class="image-input">
    <div :class="[isPreviewVisible ? 'preview' : 'preview--hidden']">
      <img
        :id="`imageinput-preview-${index}`"
        src=""
        height="200"
        alt="preview of changed input image"
      />
    </div>
    <img
      :src="`data:image/png;base64,${initialValue}`"
      height="200"
      alt="preview of initial value image"
      v-if="!isPreviewVisible && initialValue"
    />

    <div class="image-input__upload-button">
      <label :for="`imageinput-input-${index}`">
        <svg>
          <use class="icon" xlink:href="#upload" />
        </svg>
      </label>
    </div>

    <input
      v-if="!disabled"
      type="file"
      :id="`imageinput-input-${index}`"
      @change="onInputValueChange($event)"
    />
  </span>
</template>

<script>
import { imageService } from "../services/image.service";

export default {
  name: "ImageInput",
  props: ["disabled", "initialValue", "index"],
  data() {
    return {
      isPreviewVisible: false,
    };
  },
  watch: {
    initialValue() {
      const currentInputImage = document.getElementById(
        `imageinput-input-${this.index}`
      ).files[0];

      // Those 2 inputs have not the same values when we delete a card and the card image changed automatically
      // in the steps array (from parent component ) and not manually in this component's input.
      // In this situation we have to reload the image with the new initial value given from the parent.
      if (this.initialValue !== currentInputImage) {
        this.previewImage(true);
      }
    },
  },
  methods: {
    onInputValueChange(e) {
      this.$emit("imageChange", e);
      this.isPreviewVisible = true;
      this.previewImage();
    },

    previewImage(automaticImageUpdate) {
      // First we get the image input where we'll display the preview
      const preview = document.getElementById(
        `imageinput-preview-${this.index}`
      );
      // Then, we get the value to preview
      const file = automaticImageUpdate
        ? this.initialValue
        : document.getElementById(`imageinput-input-${this.index}`).files[0];

      const reader = new FileReader();

      reader.addEventListener("load", () => {
        // If it's an already-saved image coming from the DB, it's already in base64, so we don't need to convert it.
        // Else (If the user just put the image manually in the input), we use the reader to convert it in something readable.
        preview.src = imageService.isBase64(file)
          ? `data:image/png;base64,${file}`
          : reader.result;
      });

      // If the image needed to be convert to base64, we need the reader to read it correctly in the input
      if (file && !imageService.isBase64(file)) {
        reader.readAsDataURL(file);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/ImageInput";
</style>
