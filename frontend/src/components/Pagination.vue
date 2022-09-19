<template>
  <div class="pagination" v-if="this.totalPages !== 1" >
    <p
      class="pagination__symbole"
      v-if="this.totalPages && this.currentPage !== 0"
      @click="() => {this.$emit('pageIndex-previous')}"
    >
      &#60;
    </p>
    <div class="pagination__count">
      <p v-if="this.totalPages">{{ currentPage + 1 }} / {{ totalPages }}</p>
    </div>

    <p
      class="pagination__symbole"
      v-if="this.totalPages && this.currentPage + 1 !== this.totalPages"
      @click="() => {this.$emit('pageIndex-next')}"
    >
      >
    </p>
  </div>
</template>

<script>
export default {
  name: "Pagination",
  props: ["totalPages", "currentPage"],
};
</script>

<style lang="scss" scoped>
@import "../style/Pagination";
</style>
