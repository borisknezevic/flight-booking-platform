<script setup>
import { ref } from 'vue'

</script>

<template>
    <div class="demo-date-picker">
    <div class="block">
        <el-button class="size" type="primary">Search</el-button>
    </div>
    </div>
</template>

<style scoped>
.demo-date-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}

.demo-date-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}

.demo-date-picker .block:last-child {
  border-right: none;
}

.demo-date-picker .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
.size {
    width: 200px;
}
</style>

  