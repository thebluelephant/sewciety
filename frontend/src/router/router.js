import { createRouter, createWebHashHistory } from "vue-router";
import Homepage from "../pages/Homepage.vue";
import Researchpage from "../pages/Researchpage.vue";
import PatternPage from "../pages/PatternPage.vue";
import EditSbsPage from "../pages/EditSbsPage.vue";
import Callbackpage from "../pages/Authentication/Callbackpage.vue";
import Loginpage from "../pages/Loginpage.vue";
import SbsDetailsPage from "../pages/SbsDetailsPage.vue";
import PatternSubmissionPage from "../pages/PatternSubmissionPage.vue";
import { authGuard } from "../auth/auth";

const routes = [
  {
    path: "/",
    name: "Homepage",
    component: Homepage,
  },
  {
    path: "/login",
    name: "Login",
    component: Loginpage,
  },
  {
    path: "/callback",
    name: "Callback",
    component: Callbackpage,
    beforeEnter: authGuard,
  },
  {
    path: "/research",
    name: "Research",
    component: Researchpage,
    props: () => ({ research: "", brand: "" }),
  },
  {
    path: "/pattern/:id/sbs/:sbsId?/edit",
    name: "EditSbsPage",
    component: EditSbsPage,
    beforeEnter: authGuard,
  },
  {
    path: "/sbs/:sbsId",
    name: "SbsDetailsPage",
    component: SbsDetailsPage,
  },
  {
    path: "/pattern/:id",
    name: "Patternpage",
    component: PatternPage,
  },
  {
    path: "/pattern/new",
    name: "PatternSubmissionPage",
    component: PatternSubmissionPage,
    beforeEnter: authGuard,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
