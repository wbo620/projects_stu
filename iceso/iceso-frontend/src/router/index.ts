import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import IndexPage from "@/pages/IndexPage.vue";
import IndexPage2 from "@/pages/IndexPage2.vue";
import IndexPage3 from "@/pages/IndexPage3.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    component: IndexPage3,
  },
  {
    path: "/:category",
    component: IndexPage3,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
