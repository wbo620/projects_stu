<template>
  <a-config-provider>
    <!--    :theme="{-->
    <!--      token: {-->
    <!--        colorPrimary: '#eea6b7',-->
    <!--      },-->
    <!--    }"-->
    <!--  >-->
    <a-list item-layout="horizontal" :data-source="props.postList">
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
  </a-config-provider>
</template>

<script setup lang="ts">
import yaya from "../assets/yaya.jpg";
import { defineComponent, defineProps, withDefaults } from "vue";

defineComponent({
  name: "PostList",
});

interface Props {
  postList: any[];
}

const props = withDefaults(defineProps<Props>(), {
  postList: () => [],
});
</script>

<style scoped></style>
