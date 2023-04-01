import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useFlightsStore = defineStore('flights', () => {
  const flights = ref([])
  const flightSelected = ref(false)
//   const doubleCount = computed(() => count.value * 2) //getter
  const getFlights = computed(() => flights.value)
  const getFlightSelected = computed(() => flights.value)
  
  function setFlights(dataTable) {
    flights.value = dataTable
    selectFlight()
  }

  function selectFlight(){
    flightSelected.value= true
  }

  return { flights, getFlights, setFlights ,getFlightSelected}
})