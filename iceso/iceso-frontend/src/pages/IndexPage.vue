<script setup lang="ts">
import { ref, watchEffect } from "vue";

import PictureList from "@/components/PictureList.vue";
import MyDivider from "@/components/MyDivider.vue";
import PostList from "@/components/PostList.vue";
import UserList from "@/components/UserList.vue";
import { useRoute, useRouter } from "vue-router";
import myAxios from "@/plugins/myAxios";

const postlist = ref([]);
const picturelist = ref([]);
const userlist = ref([]);
// //展示文章信息
myAxios.post("/post/list/page/vo", {}).then((re: any) => {
  postlist.value = re.records;
});

const router = useRouter();
const route = useRoute();
const searchText = ref(route.query.text || "");
// 初始地址栏参数
const initSearchParams = {
  pageSize: 10,
  pageNum: 1,
  text: "",
};
// 动态获取路由参数
const activeKey = route.params.category;
const searchParams = ref(initSearchParams);

// 地址栏参数与页面对应
watchEffect(() => {
  searchParams.value = {
    ...initSearchParams,
    text: route.query.text,
  } as any;
});
// 搜索栏事件
const onSearch = (value: string) => {
  alert(value);
  router.push({
    query: searchParams.value,
  });
};
//列表页切换事件
const onTabChange = (key: string) => {
  router.push({
    path: `/${key}`,
    query: searchParams.value,
  });
};
</script>
<template>
  <div class="index-page">
    <a-input-search
      v-model:value="searchText"
      placeholder="请输入搜索关键词"
      enter-button="搜索"
      size="large"
      @search="onSearch"
    />
    <MyDivider />
    <a-tabs v-model:activeKey="activeKey" @change="onTabChange">
      <a-tab-pane key="post" tab="文章">
        <PostList :post-list="postlist" />
      </a-tab-pane>
      <a-tab-pane key="picture" tab="图片">
        <PictureList :picture-list="picturelist" />
      </a-tab-pane>
      <a-tab-pane key="user" tab="用户">
        <UserList :user-list="userlist" />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>
