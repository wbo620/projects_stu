<style scoped>
.yaya {
  width: 100px;
}
</style>
<template>
  图片列表<img :src="yaya" class="yaya" />
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
    :data-source="props.pictureList"
  >
    <template #renderItem="{ item }">
      <a-list-item>
        <a-list-item-meta description="">
          <template #title>
            <a href="https://www.antdv.com/">{{ item.title }}</a>
          </template>
          <template #avatar>
            <a-avatar src="yaya" />
          </template>
        </a-list-item-meta>
      </a-list-item>
    </template>
  </a-list>
</template>
<script setup lang="ts">
import yaya from "../assets/yaya.jpg";
import { defineProps, withDefaults } from "vue";

interface Props {
  pictureList: [];
}

const props = withDefaults(defineProps<Props>(), {
  pictureList: () => [],
});
</script>
