import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useProfileStore = defineStore('profile', () => {
    const tickets = ref([])
    const getTickets = computed(() => tickets.value)

    function setTickets(tickets) {
        airlines.value = tickets
    }

    function addTicket(ticket){
        ticket.value.push(ticket)
    }

    return { tickets, getTickets, setTickets,addTicket }
})