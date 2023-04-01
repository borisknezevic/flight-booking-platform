<script setup>
import { ref ,onMounted} from 'vue'
import axios from 'axios';



const value = ref('')
const options = ref([])
const emit = defineEmits(['searchParameterDestination'])

const emitDestination = () => {
  emit('searchParameterDestination',value.value)
}

onMounted(() => {
  fillDestinationOptions();
})


const fillDestinationOptions = () => {
  axios
    .get('http://localhost:8080/api/v1/airports/cities')
    .then((response)=> {
      options.value = response.data
    })
}

</script>

<template>
    <div class="demo-date-picker">
    <div class="block">
    <el-select 
      v-model="value" 
      filterable 
      @change="emitDestination"
      placeholder="To:">
      <el-option
        v-for="item in options"
        :key="item.cityName"
        :label="item.cityName"
        :value="item.cityName"
      />
    </el-select>
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

  