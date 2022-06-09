<template>
  <div class="popin" v-if="showPopin">
    <div class="popin__container">
      <span class="body">
        {{ description }}
      </span>
      <span class="footer">
        <basic-button
          :title="$t('common.cancel')"
          @click="this.$emit('onCancel')"
          type="action"
        />
        <basic-button
          :title="$t('common.confirm')"
          @click="this.$emit('onConfirm')"
          type="action"
        />
      </span>
    </div>
  </div>
</template>

<script>
import BasicButton from "./Basic-Button.vue";

export default {
  components: { BasicButton },
  name: "Popin",
  props: ["showPopin", "description"],
};
</script>

<style lang="scss" scoped>
@import "../style/Popin";
</style>
