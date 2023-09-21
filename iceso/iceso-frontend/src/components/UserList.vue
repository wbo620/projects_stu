<template>
  用户列表
  <div class="UserList">
    <a-list
      item-layout="horizontal"
      :grid="{
        gutter: 16,
        xs: 1,
        sm: 2,
        md: 4,
        lg: 4,
        xl: 6,
        xxl: 3,
      }"
      :data-source="props.userList"
    >
      <template #renderItem="{ item }">
        <a-list>
          <a-card hoverable class="userBody">
            <template #cover>
              <img alt="example" :src="yaya" />
            </template>
            <a-card-meta class="userText" :title="item.userName">
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
.userText {
  height: 100px;
}
.userBody {
  margin: 5px;
}
</style>
