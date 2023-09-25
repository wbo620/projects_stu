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
      :data-source="props.videoList"
      :grid="{ gutter: 16, xs: 1, sm: 2, md: 4, lg: 4, xl: 3, xxl: 3 }"
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
  </div>
</template>

<script setup lang="ts">
import { defineProps, withDefaults } from "vue";

interface Props {
  videoList: [];
}

const props = withDefaults(defineProps<Props>(), {
  videoList: () => [],
});
</script>
