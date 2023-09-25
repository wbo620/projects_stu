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
      <a-tab-pane key="video" tab="视频">
        <VideoList :video-list="videolist" />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>
<script setup lang="ts">
import { ref, watchEffect } from "vue";

import PictureList from "@/components/PictureList.vue";
import MyDivider from "@/components/MyDivider.vue";
import PostList from "@/components/PostList.vue";
import UserList from "@/components/UserList.vue";
import { useRoute, useRouter } from "vue-router";
import myAxios from "@/plugins/myAxios";
import { message } from "ant-design-vue";
import VideoList from "@/components/VideoList.vue";

const postlist = ref([]);
const picturelist = ref([]);
const userlist = ref([]);
const videolist = ref([]);

const router = useRouter();
const route = useRoute();
// 动态获取路由参数
const activeKey = route.params.category;

// 初始地址栏参数
const initSearchParams = {
  type: activeKey,
  text: "",
  pageSize: 10,
  pageNum: 1,
};
const searchText = ref(route.query.text || "");
/*//加载数据
myAxios.post("post/list/page/vo", {}).then((re: any) => {
  postlist.value = re.records;
});
myAxios.post("user/list/page/vo", {}).then((re: any) => {
  userlist.value = re.records;
});*/

/**
 * 加载聚合数据
 * @param params
 */
const loadAllData = (params: any) => {
  // const { type = "post" } = params;
  // if (!type) {
  //   message.error("类别为空");
  //   return;
  // }
  const query = {
    ...params,
    searchText: params.text,
  };
  myAxios.post("search/all", query).then((res: any) => {
    postlist.value = res.postList;
    userlist.value = res.userList;
    picturelist.value = res.pictureList;
    videolist.value = res.videoList;
  });
};
/**
 * 加载单类数据
 * @param params
 */
const loadData = (params: any) => {
  const { type = "post" } = params;
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
      postlist.value = res.dataList;
    } else if (type === "user") {
      userlist.value = res.dataList;
    } else if (type === "picture") {
      picturelist.value = res.dataList;
    } else if (type == "video") {
      videolist.value = res.dataList;
    }
  });
};

//初始化查找参数
const searchParams = ref(initSearchParams);
// //首次请求加载页面
//loadAllData(initSearchParams);
// 地址栏参数与页面对应
//作用：当地址改变，重新加载页面
watchEffect(() => {
  searchParams.value = {
    ...initSearchParams,
    text: route.query.text,
    type: route.params.category,
  } as any;
  //点击搜索栏再加载页面
  loadData(searchParams.value);
});
// 搜索栏事件：点击搜索，触发事件
const onSearch = (value: string) => {
  router.push({
    query: {
      ...searchParams.value,
      text: value,
    },
  });
  //loadData(searchParams.value);
};
//列表页切换事件：地址参数改变
const onTabChange = (key: string) => {
  router.push({
    path: `/${key}`,
    query: searchParams.value,
  });
};
</script>
