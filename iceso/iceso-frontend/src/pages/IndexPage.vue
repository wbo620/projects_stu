<template>
  <div class="index-page">
    <div>
      <a-auto-complete
        v-model:value="searchText"
        :options="options"
        @select="onSearch"
        size="large"
        style="width: 90%"
      >
        <a-input-search
          v-model:value="searchParams"
          placeholder="请输入搜索关键词"
          enter-button="搜索"
          size="large"
          @search="onSearch"
        />
      </a-auto-complete>
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
  </div>
  <!-- 添加回到顶部按钮 -->
  <a-back-top />
  <strong style="color: rgba(64, 64, 64, 0.6)" />
</template>

<script setup lang="ts">
import { ref, watchEffect } from "vue";
import { useRoute, useRouter } from "vue-router";
import myAxios from "@/plugins/myAxios";
import { message } from "ant-design-vue";
import UserList from "@/components/UserList.vue";
import VideoList from "@/components/VideoList.vue";
import PictureList from "@/components/PictureList.vue";
import PostList from "@/components/PostList.vue";
import MyDivider from "@/components/MyDivider.vue";

const postlist = ref([]);
const picturelist = ref([]);
const userlist = ref([]);
const videolist = ref([]);
const options = ref<any>([]);
// 初始化一个标志来跟踪是否是第一次加载
let isFirstLoad = true;

const router = useRouter();
const route = useRoute();
// 动态获取路由参数
const activeKey = route.params.category;
// 初始地址栏参数
const initSearchParams = {
  type: activeKey,
  text: "",
  pageSize: 50,
  pageNum: 1,
};
//初始化查找参数
const searchParams = ref(initSearchParams);
//查找文本
const searchText = ref(route.query.text || "");
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

  if (isFirstLoad) {
    // 第一次加载时默认加载"post"
    searchParams.value.type = "post";
    isFirstLoad = false;
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

// 监听地址栏：地址栏参数与页面对应
//作用：当地址改变，重新加载页面
watchEffect(() => {
  searchParams.value = {
    ...initSearchParams,
    text: route.query.text,
    type: route.params.category,
  } as any;
  //路径改变，加载页面
  loadData(searchParams.value);
});

// 搜索栏事件：点击搜索，路径改变
const onSearch = (value: string) => {
  router.push({
    query: {
      ...searchParams.value,
      text: value,
    },
  });
};
//逻辑
//点击搜索，搜索参数text发生改变，路径改变，被监听，执行后端请求
//Tab改变，activeKey改变，路径改变，被监听，执行搜索

//列表页切换事件：地址参数改变
const onTabChange = (key: string) => {
  router.push({
    path: `/${key}`,
    query: {
      ...searchParams.value,
      type: `${key}`,
    },
  });
};
</script>

<style scoped>
#spin {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
