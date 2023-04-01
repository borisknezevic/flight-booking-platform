<script setup>
import { ref } from 'vue'

const emit = defineEmits(['searchParameterDate'])
const date = ref('')


const emitDate = () => {
  emit('searchParameterDate',date.value)
}


</script>

<template>
    <div class="demo-date-picker">
    <div class="block">
      <el-date-picker
        v-model="date"
        type="date"
        placeholder="Flight date"
        @change="emitDate()"
        value-format="YYYY-MM-DD"
      />
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
</style>