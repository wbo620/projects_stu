import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import IndexPage from "@/pages/IndexPage.vue";
import IndexPage2 from "@/pages/IndexPage2.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    component: IndexPage2,
  },
  {
    path: "/:category",
    component: IndexPage2,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
