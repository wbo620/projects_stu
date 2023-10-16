<template>
  <a-config-provider>
    <a-list
      item-layout="horizontal"
      :data-source="pagedPictureList"
      style="margin-bottom: 20px"
      :grid="{
        gutter: 16,
        xs: 1,
        sm: 2,
        md: 4,
        lg: 4,
        xl: 5,
        xxl: 3,
      }"
    >
      <template #renderItem="{ item }">
        <a-list>
          <div class="row">
            <a-card hoverable class="Body">
              <template #cover>
                <img :alt="item.title" :src="item.url" class="img" />
              </template>
              <a-card-meta class="Text">
                <template #title>
                  <a-tooltip :title="item.title">
                    <span>{{ item.title }}</span>
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
      :total="props.pictureList.length"
      :page-size="pageSize"
    />
  </a-config-provider>
</template>

<script setup lang="ts">
import { computed, defineProps, ref, withDefaults } from "vue";
import MyPagination from "@/components/MyPagination.vue";

interface Props {
  pictureList: [];
}

const props = withDefaults(defineProps<Props>(), {
  pictureList: () => [],
});

const currentPage = ref(1);
const pageSize = 20;

const pagedPictureList = computed(() => {
  const startIndex = (currentPage.value - 1) * pageSize;
  const endIndex = startIndex + pageSize;
  return props.pictureList.slice(startIndex, endIndex);
});
</script>

<style scoped>
.Body {
  margin: 5px;
  height: 100%;
}

.Text {
  text-align: center;
}

.row {
  height: 300px;
}

.img {
  height: 220px;
}
</style>
