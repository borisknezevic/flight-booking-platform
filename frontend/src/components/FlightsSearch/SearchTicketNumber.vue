<script setup>
import { ref } from 'vue'
const input = ref('')

const emit = defineEmits('searchParameterPassengerNum')
const emitPassengerNum = () => {
  emit('searchParameterPassengerNum', input.value)
}
</script>

<template>
    <div class="demo-date-picker">
      <div class="block">
          <el-input 
            class="size" 
            v-model="input" 
            @input="emitPassengerNum"
            placeholder="Ticket number" />
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
.size{
    width: 220px;
}
</style>

  