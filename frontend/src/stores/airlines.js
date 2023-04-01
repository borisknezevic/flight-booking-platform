import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useAirlinesStore = defineStore('airlines', () => {
  const airlines = ref([])
  const getAirlines = computed(() => airlines.value)

  function setAirlines(data) {
    airlines.value = data
  }

  return { airlines, getAirlines, setAirlines }
})