<template>
  <div class="contact-page">
    <div class="card">
      <span class="card__header">
        <p class="title title--primary">
          {{ $t("contact.title") }}
        </p>
        <p>{{ $t("contact.subtitle") }}</p>
      </span>
      <form ref="form" @submit.prevent="onSendEmail">
        <span class="card__form">
          <span class="card__form__name">
            <label for="name"> {{ $t("contact.name") }} </label>
            <input
              type="text"
              name="name"
              id="name"
              class="name-input input input--neumorphic"
            />
          </span>

          <span class="card__form__email">
            <label for="email"> {{ $t("contact.email") }}</label>
            <input
              type="email"
              name="email"
              id="email"
              minlength="5"
              required
              class="email-input input input--neumorphic"
            />
          </span>

          <span class="card__form__contact">
            <label for="contact">{{ $t("contact.message") }}</label>
            <textarea
              name="contact"
              id="contact"
              cols="30"
              rows="10"
              minlength="5"
              required
              class="contact-textarea textarea textarea--neumorphic"
            ></textarea>
          </span>
        </span>
        <span class="card__footer">
          <button type="submit">
            <basic-button type="action" :title="$t('common.send')" />
          </button>
        </span>
      </form>
    </div>
  </div>
</template>

<script>
import { emailService } from "../services/email.service";
import BasicButton from "../components/Basic-Button.vue";

export default {
  name: "Contactpage",
  components: {
    BasicButton,
  },
  methods: {
    onSendEmail() {
      this.emitter.emit("displayLoader");
      emailService.sendEmail(this.$refs.form).then((resp) => {
        this.emitter.emit("hideLoader");
        this.emitter.emit(
          "launch-alert",
          resp.status === 200
            ? this.$t("contact.success")
            : this.$t("contact.error")
        );
        if (resp.status === 200) {
          this.$router.push({ name: "Homepage" });
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "../style/Contactpage";
</style>
