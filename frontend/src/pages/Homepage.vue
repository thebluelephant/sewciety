<template>
  <div class="homepage">
    <div class="homepage__header">
      <div class="left-column">
        <p class="title title--primary">
          <span class="title--pink"> {{ $t("homepage.slogan-1") }} </span
          >{{ $t("homepage.slogan-2") }}
        </p>
        <p class="description">
          {{ $t("homepage.description1") }} <br />
          <br />
          {{ $t("homepage.description2") }} <br />
        </p>
        <HomePatternResearch />
      </div>
      <div class="right-column">
        <img
          :src="require('../assets/image/homepage-woman.png')"
          alt="homepage woman"
        />
      </div>
    </div>
    <div class="homepage__body">
      <HowItWorks />
    </div>
  </div>
</template>

<script>
import router from "../router/router.js";
import { useMeta } from "vue-meta";
import HowItWorks from "../components/HowItWorks.vue";
import HomePatternResearch from "../components/HomePatternResearch.vue";

export default {
  name: "Homepage",
  setup() {
    useMeta({
      title: "Plateforme participative de tutoriels couture !",
      content:
        "Sewciety est la première plateforme participative de tutoriels couture axée sur la réalisation des patrons. Trouvez le tuto' adapté à votre ouvrage, ou partagez le votre !",
    });
  },
  components: { HowItWorks, HomePatternResearch },
  methods: {
    redirectOnPatternSubmission() {
      router.push({
        name: "PatternSubmissionPage",
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Homepage";
</style>
