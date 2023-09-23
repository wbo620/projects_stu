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
<template>
  <div class="PictureList">
    <a-list
      item-layout="horizontal"
      :grid="{
        gutter: 16,
        xs: 1,
        sm: 2,
        md: 4,
        lg: 4,
        xl: 5,
        xxl: 3,
      }"
      :data-source="props.pictureList"
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
  </div>
</template>

<script setup lang="ts">
import { defineProps, withDefaults } from "vue";

interface Props {
  pictureList: [];
}

const props = withDefaults(defineProps<Props>(), {
  pictureList: () => [],
});
</script>
