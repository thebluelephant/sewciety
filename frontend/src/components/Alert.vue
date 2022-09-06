<template>
  <div :class="[show ? 'alert' : 'alert--hidden']">
    <div
      :class="[
        show
          ? 'alert__container alert__container--visible'
          : 'alert__container alert__container--hidden',
      ]"
    >
      {{ description }}
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  name: "Alert",
  mounted() {
    this.emitter.on("launch-alert", (payload) => {
      this.show = true;
      this.description = payload;
      setTimeout(() => {
        this.show = false;
        this.description = "";
      }, 5000);
    });
  },

  data() {
    return {
      description: "",
      show: false,
    };
  },
  methods: {},
};
</script>

<style lang="scss" scoped>
@import "../style/Alert";
</style>
