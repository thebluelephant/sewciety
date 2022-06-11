<template>
  <div class="homepage">
    <div class="title title--primary" v-if="username">
      {{ $t("common.hello") }}
      <span class="title__username">{{ username }}</span
      >!
    </div>
    <PatternResearch />
  </div>
</template>

<script>
import PatternResearch from "../components/PatternResearch.vue";
import { userService } from "../services/user.service";

export default {
  name: "Homepage",
  components: {
    PatternResearch,
  },
  data() {
    return {
      file: "",
      username: null,
    };
  },
  mounted() {
    userService
      .getCurrentUserUsername()
      .then((username) => (this.username = username));
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Homepage";
</style>
