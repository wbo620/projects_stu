<script setup lang="ts">
import { ref, watchEffect } from "vue";

import PictureList from "@/components/PictureList.vue";
import MyDivider from "@/components/MyDivider.vue";
import PostList from "@/components/PostList.vue";
import UserList from "@/components/UserList.vue";
import { useRoute, useRouter } from "vue-router";
import myAxios from "@/plugins/myAxios";
import { message } from "ant-design-vue";

const postlist = ref([]);
const picturelist = ref([]);
const userlist = ref([]);
/*//展示文章信息
myAxios.post("post/list/page/vo", {}).then((re: any) => {
  postlist.value = re.records;
});
myAxios.post("user/list/page/vo", {}).then((re: any) => {
  userlist.value = re.records;
});*/
/**
 * 加载单类数据
 * @param params
 */
const loadData = (params: any) => {
  const { type = "psost" } = params;
  if (!type) {
    message.error("类别为空");
    return;
  }
  const query = {
    ...params,
    searchText: params.text,
  };
  myAxios.post("search/all", query).then((res: any) => {
    if (type === "post") {
      postlist.value = res.postList;
    } else if (type === "user") {
      userlist.value = res.userList;
    } else if (type === "picture") {
      picturelist.value = res.pictureList;
    }
  });
};
/**
 * 加载聚合数据
 * @param params
 */
const loadAllData = (params: any) => {
  const query = {
    ...params,
    searchText: params.text,
  };
  myAxios.post("search/all", query).then((res: any) => {
    postlist.value = res.dataList;

    userlist.value = res.dataList;

    picturelist.value = res.dataList;
  });
};

const router = useRouter();
const route = useRoute();
const searchText = ref(route.query.text || "");
// 动态获取路由参数
const activeKey = route.params.category;
// 初始地址栏参数
const initSearchParams = {
  type: activeKey,
  text: "",
  pageSize: 10,
  pageNum: 1,
};

const searchParams = ref(initSearchParams);

// 地址栏参数与页面对应
watchEffect(() => {
  searchParams.value = {
    ...initSearchParams,
    text: route.query.text,
    type: route.params.category,
  } as any;
  loadData(searchParams.value);
});
// 搜索栏事件
const onSearch = (value: string) => {
  router.push({
    query: {
      ...searchParams.value,
      text: value,
    },
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
