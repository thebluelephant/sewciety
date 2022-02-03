import { createRouter, createWebHashHistory } from "vue-router";
import Homepage from "../pages/Homepage.vue";
import Researchpage from "../pages/Researchpage.vue";
import PatternPage from "../pages/PatternPage.vue"

const routes = [
  {
    path: "/",
    name: "Homepage",
    component: Homepage,
  },
  {
    path: "/research",
    name: "Research",
    component: Researchpage,
    props : () => ({research : "", brand : ""})
  },
  {
    path: "/pattern/:id",
    name: "Patternpage",
    component: PatternPage,
  },
  /* 
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about"  '../views/About.vue')
  } */
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
