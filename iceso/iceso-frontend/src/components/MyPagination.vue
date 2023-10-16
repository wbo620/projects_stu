<template>
  <div class="top">
    <a-pagination
      :current="currentPage"
      :total="total"
      :page-size="pageSize"
      @change="handlePageChange"
      class="pagination-bottom"
    />
  </div>
</template>

<script setup>
import { defineEmits, defineProps } from "vue";

const props = defineProps({
  currentPage: Number,
  total: Number,
  pageSize: Number,
});

const emit = defineEmits(["update:currentPage"]);

const handlePageChange = (page) => {
  emit("update:currentPage", page);
  // 滚动到页面顶部，持续时间为0.1秒
  window.scrollTo({ top: 0, behavior: "auto" });
};
</script>

<style>
.pagination-bottom {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: white;
  padding: 16px;
  display: flex;
  justify-content: center;
}
</style>
