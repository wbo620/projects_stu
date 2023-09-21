<template>
  用户列表
  <div class="UserList">
    <a-list item-layout="horizontal" :data-source="props.userList">
      <template #renderItem="{ item }">
        <a-list class="card-wrapper">
          <a-card hoverable style="width: 200px">
            <template #cover>
              <img alt="example" :src="yaya" />
            </template>
            <a-card-meta :title="item.userName">
              <template #description>{{ item.userProfile }}</template>
            </a-card-meta>
          </a-card>
        </a-list>
      </template>
    </a-list>
  </div>
</template>
<script setup lang="ts">
import { defineProps, withDefaults } from "vue";
import yaya from "../assets/yaya.jpg";

interface Props {
  userList: any[];
}

const props = withDefaults(defineProps<Props>(), {
  userList: () => [],
});
</script>

<style scoped>
.card-wrapper {
  width: 20%; /* 设置每个卡片的宽度为四分之一，以每行显示四个卡片 */
  box-sizing: border-box; /* 防止边距和边框导致卡片换行 */
  padding: 8px; /* 添加一些内边距，以控制卡片之间的间距 */
  display: inline-block; /* 让卡片并排显示 */
  margin: 20px;
}
</style>
