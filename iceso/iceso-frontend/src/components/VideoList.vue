<style scoped>
.Body {
  margin: 5px;
  height: 100%;
}

.Text {
  text-align: center;
}
.row {
  height: 350px;
}

.img {
  height: 290px;
  width: 100%;
}
</style>
<template>
  <div class="VideoList">
    <a-list
      item-layout="horizontal"
      :data-source="pagedVideoList"
      :grid="{ gutter: 16, xs: 1, sm: 2, md: 4, lg: 4, xl: 3, xxl: 3 }"
      style="margin-bottom: 20px"
    >
      <template #renderItem="{ item }">
        <a-list>
          <div class="row">
            <a-card hoverable class="Body">
              <template #cover>
                <a :href="item.url" target="_blank">
                  <img :src="item.picUrl" :alt="item.title" class="img" />
                </a>
              </template>
              <a-card-meta class="Text">
                <template #title>
                  <a-tooltip :title="item.title">
                    <span>
                      <a :href="item.url" target="_blank">{{ item.title }}</a>
                    </span>
                  </a-tooltip>
                </template>
              </a-card-meta>
            </a-card>
          </div>
        </a-list>
      </template>
    </a-list>
    <MyPagination
      v-model:currentPage="currentPage"
      :total="props.videoList.length"
      :page-size="pageSize"
    />
  </div>
</template>

<script setup lang="ts">
import { computed, defineProps, ref, withDefaults } from "vue";
import MyPagination from "@/components/MyPagination.vue";

interface Props {
  videoList: [];
}

const props = withDefaults(defineProps<Props>(), {
  videoList: () => [],
});
const currentPage = ref(1);
const pageSize = 18;

const pagedVideoList = computed(() => {
  const startIndex = (currentPage.value - 1) * pageSize;
  const endIndex = startIndex + pageSize;
  return props.videoList.slice(startIndex, endIndex);
});
</script>
