<template>
  <a-config-provider>
    <a-list
      item-layout="horizontal"
      :data-source="pagedPostList"
      style="margin-bottom: 20px"
    >
      <template #renderItem="{ item }">
        <a-list-item>
          <a-list-item-meta>
            <template #title>
              <a href="https://www.antdv.com/" v-html="item.title"></a>
            </template>
            <template #avatar>
              <a-avatar :src="yaya" />
            </template>
            <template #description>
              <div v-html="item.content" style="margin-bottom: 10px"></div>
              <template v-for="tag in item.tagList" :key="tag">
                <a-tag>
                  {{ tag }}
                </a-tag>
              </template>
            </template>
          </a-list-item-meta>
        </a-list-item>
      </template>
    </a-list>
    <MyPagination
      v-model:currentPage="currentPage"
      :total="props.postList.length"
      :page-size="pageSize"
    />
  </a-config-provider>
</template>

<script setup lang="ts">
import { computed, defineProps, ref, withDefaults } from "vue";
import MyPagination from "@/components/MyPagination.vue";
import yaya from "../assets/yaya.jpg";

interface Props {
  postList: [];
}

const props = withDefaults(defineProps<Props>(), {
  postList: () => [],
});

const currentPage = ref(1);
const pageSize = 20;

const pagedPostList = computed(() => {
  const startIndex = (currentPage.value - 1) * pageSize;
  const endIndex = startIndex + pageSize;
  return props.postList.slice(startIndex, endIndex);
});
</script>

<style scoped></style>
