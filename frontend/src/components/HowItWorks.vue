<template>
  <div class="how-it-works">
    <div class="step" v-for="step in steps" :key="step.icon">
      <div class="step__circle">
        <svg>
          <use class="open-icon" :xlink:href="`#${step.icon}`" />
        </svg>
      </div>
      <p class="step__title title title__secondary">{{ step.title }}</p>
      <p class="step__description">{{ step.description }}</p>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  name: "HowItWorks",
  data() {
    return {
      steps: [
        {
          icon: "search",
          title: "Trouve ton patron !",
          description:
            "Trouve la référence de ton patron grâce à la recherche, ou propose la grâce au formulaire dédié.",
        },
        {
          icon: "register",
          title: "Inscrit toi",
          description: "Bien plus utile quand tu souhaites proposer ton propre tuto' ; en deux temps mouvements, c'est fait !",
        },
        {
          icon: "write",
          title: "Crée ton tuto'",
          description:
            "Ajoute les étapes de réalisation de ton ouvrage ; tu peux même enregistrer ton tuto' pour y revenir plus tard, pratique ! ;)",
        },
        {
          icon: "share",
          title: "Partage !",
          description:
            "Et hop - Une fois validé, n'hésite pas à partager ton tuto' !",
        },
      ],
    };
  },
  methods: {},
};
</script>

<style lang="scss" scoped>
@import "../style/HowItWorks";
</style>
