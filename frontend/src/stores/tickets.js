import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { timeUnits } from 'element-plus'



export const useTicketsStore = defineStore('tickets', () => {
  const flight = ref([])  
  const tickets = ref([])

  const flightSelected = ref(false)

  const getTickets = computed(() => airlines.value)
  const getFlight = computed(() => flight.value)
  const getFlightSelected = computed(() => flightSelected.value)

  function setFlight(f){
    flight.value = f
    this.selectFlight()
  }

  function addTicket(ticket) {
    tickets.value.push(ticket)
  }

  function clearTickets(){
    tickets.value = []
  }

  function selectFlight() {
    flightSelected.value = true
  }

  function deselectFlight() {
    flightSelected.value = false
  }

  return { flightSelected,flight, tickets, getTickets,getFlight,setFlight,addTicket,selectFlight,deselectFlight,getFlightSelected,clearTickets }
})