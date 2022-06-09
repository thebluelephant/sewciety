<template>
  <span class="image-input">
    <div :class="[isPreviewVisible ? 'preview' : 'preview--hidden']">
      <img
        :id="`imageinput-preview-${index}`"
        src=""
        height="200"
        alt="preview of image"
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
      @change="onImageChange($event)"
    />
  </span>
</template>

<script>
export default {
  name: "ImageInput",
  props: ["disabled", "initialValue", "index"],
  data() {
    return {
      isPreviewVisible: false,
    };
  },
  methods: {
    onImageChange(e) {
      this.$emit("imageChange", e);
      this.isPreviewVisible = true;
      this.previewImage();
    },
    previewImage() {
      const preview = document.getElementById(
        `imageinput-preview-${this.index}`
      );
      const file = document.getElementById(`imageinput-input-${this.index}`)
        .files[0];
      const reader = new FileReader();

      // on convertit l'image en une chaîne de caractères base64
      reader.addEventListener("load", () => {
        preview.src = reader.result;
      });

      if (file) {
        reader.readAsDataURL(file);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/ImageInput";
</style>
