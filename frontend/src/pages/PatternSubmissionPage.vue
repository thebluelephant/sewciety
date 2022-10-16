<template>
  <form @submit.prevent="onSubmit">
    <div class="pattern-submission-page">
      <p class="title title--primary">
        {{ $t("patternsubmissionpage.submit-pattern") }}
      </p>
      <div class="form-container">
        <span class="form-container--inline">
          <span class="form-container__field form-container__field--inline">
            <label for="name">{{ $t("patternsubmissionpage.name") }}</label>
            <input
              id="name"
              class="input input--neumorphic"
              type="text"
              required
              minlength="5"
              v-model="name"
            />
          </span>
          <span class="form-container__field form-container__field--inline">
            <label for="brand">{{ $t("patternsubmissionpage.brand") }}</label>
            <input
              id="brand"
              class="input input--neumorphic"
              type="text"
              required
              minlength="5"
              v-model="brand"
            />
          </span>
        </span>

        <span class="form-container__field">
          <label for="brand">{{ $t("patternsubmissionpage.image") }}</label>
          <image-input @imageChange="onImageChange($event)" />
        </span>
        <span class="form-container__field form-container__field--column">
          <label for="description">{{
            $t("patternsubmissionpage.description")
          }}</label>
          <textarea
            id="description"
            class="textarea textarea--neumorphic"
            type="text"
            v-model="description"
            required
            minlength="5"
          />
        </span>
        <span class="form-container__field" id="difficulty-level">
          <label for="difficulty">{{
            $t("patternsubmissionpage.difficulty-level")
          }}</label>
          <select name="difficulty" id="difficulty" v-model="difficulty">
            <option selected>0</option>
            <option v-for="index in 5" :key="index">{{ index }}</option>
          </select>
        </span>
      </div>
      <span class="submit-container">
        <button type="submit">
          <basic-button
            :disable="!validateFields()"
            :title="$t('common.submit')"
            type="navigation"
          />
        </button>
      </span>
    </div>
  </form>
</template>

<script>
import BasicButton from "../components/Basic-Button.vue";
import ImageInput from "../components/ImageInput.vue";
import { apiCall } from "../services/patterns-api";
import { imageService } from "../services/image.service";
import { useMeta } from "vue-meta";

export default {
  components: { BasicButton, ImageInput },
  name: "PatternSubmissionPage",
  setup() {
    useMeta({
      title: "Répertorier un patron de couture",
      content:
        "Votre patron de couture n'est pas encore disponible sur Sewciety ? Soumettez-le pour proposer à votre tour un tutoriel de réalisation ",
    });
  },
  data() {
    return {
      name: "",
      brand: "",
      description: "",
      difficulty: 0,
      image: null,
    };
  },
  methods: {
    onImageChange(image) {
      imageService.fileToBase64(image).then((result) => (this.image = result));
    },
    onSubmit() {
      this.emitter.emit("displayLoader");
      const pattern = {
        name: this.name,
        brand: this.brand,
        description: this.description,
        difficulty: parseInt(this.difficulty),
        image: this.image,
      };
      apiCall.submitNewPattern(pattern).then((response) => {
        if (response) {
          this.emitter.emit("hideLoader");
          this.$router.back();
          this.emitter.emit(
            "launch-alert",
            this.$t("patternsubmissionpage.success-submission-alert")
          );
        }
      });
    },
    validateFields() {
      return (
        this.name.length &&
        this.brand.length &&
        this.description.length &&
        this.image
      );
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/PatternSubmissionPage.scss";
</style>
