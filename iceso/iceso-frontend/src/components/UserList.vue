<template>
  <a-config-provider>
    <a-list
      item-layout="horizontal"
      :grid="{
        gutter: 16,
        xs: 1,
        sm: 2,
        md: 4,
        lg: 4,
        xl: 4,
        xxl: 3,
      }"
      :data-source="pagedUserList"
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
    <MyPagination
      v-model:currentPage="currentPage"
      :total="props.userList.length"
      :page-size="pageSize"
    />
  </a-config-provider>
</template>
<script setup lang="ts">
import { computed, defineProps, ref, withDefaults } from "vue";
import yaya from "../assets/yaya.jpg";
import MyPagination from "@/components/MyPagination.vue";

interface Props {
  userList: any[];
}

const props = withDefaults(defineProps<Props>(), {
  userList: () => [],
});

const currentPage = ref(1);
const pageSize = 20;

const pagedUserList = computed(() => {
  const startIndex = (currentPage.value - 1) * pageSize;
  const endIndex = startIndex + pageSize;
  return props.userList.slice(startIndex, endIndex);
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
