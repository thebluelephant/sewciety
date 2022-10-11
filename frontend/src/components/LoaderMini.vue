<template>
  <div class="loader-mini">
    <p class="loader-mini__mention">{{ $t("loader-mini.mention") }} </p>
    <div class="loader-mini__loader">
      <div></div>
      <div></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoaderMini",
};
</script>

<style lang="scss" scoped>
@import "../style/LoaderMini";
</style>
