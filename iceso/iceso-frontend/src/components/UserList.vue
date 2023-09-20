<template>
  用户列表
  <div class="UserList">
    <a-list item-layout="horizontal" :data-source="props.userList">
      <template #renderItem="{ item }">
        <a-list-item>
          <a-list-item-meta :description="item.content">
            <template #title>
              <a href="https://www.antdv.com/">{{ item.title }}</a>
            </template>
            <template #avatar>
              <a-avatar :src="gege" />
            </template>
          </a-list-item-meta>
        </a-list-item>
      </template>
    </a-list>
  </div>
</template>
<script setup lang="ts">
import gege from "../assets/yaya.jpg";
import { defineComponent, defineProps, withDefaults } from "vue";

interface Props {
  userList: any[];
}

const props = withDefaults(defineProps<Props>(), {
  userList: () => [],
});
defineComponent({
  name: "UserList",
});
</script>

<style scoped>
.gege {
  width: 200px;
}
</style>
