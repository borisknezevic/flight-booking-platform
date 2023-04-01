<script setup>
import {ref,reactive} from 'vue'
import {useTicketsStore} from '../stores/tickets'
import { useUsersStore } from '../stores/user'
import { storeToRefs } from 'pinia'
import axios from 'axios';
import router from '../router';

const userStore = useUsersStore()
const store = useTicketsStore()
const {flight,tickets} =  storeToRefs(store)

const form = reactive({
    firstName:'',
    lastName:'',
})

const tableData = tickets

const AddTIcket = () => {
    if(store.getFlightSelected){
        console.log("Let izabran")
        const tFlight = store.getFlight
        const ticket = {
            firstName: form.firstName,
            lastName: form.lastName,
            price: tFlight.price,
            flightId: tFlight.flightId
        }
        console.log(ticket)
        store.addTicket(ticket)
        console.log(tickets)
    } else {
        console.log("Let nije izabran")
    }
}

const buyTickets = () => {
    let tickets = tableData.value.map( (passenger) => ({
        passengerFirstName : passenger.firstName,
        passengerLastName: passenger.lastName,
    }))

    console.log(tickets)
    console.log(store.getFlight.flightId)
    axios
        .post('http://localhost:8080/api/v1/tickets',{
            flightId: store.getFlight.flightId,
            tickets
        },{
            headers:{
                Authorization: "Bearer " + userStore.getAccessToken
            }
        })
        .then((response) => {
            console.log(response)
            store.clearTickets()
            router.push("/profile")
        })
}

</script>
<template>
    <div class="info">
        <h3>{{ flight.flightId }}</h3>
        <h3>From: {{ flight.currentCity }}</h3>
        <h3>To: {{ flight.destinationCity }}</h3>
        <h3>Airline: {{ flight.airlineName }}</h3>
    </div>
    <div>Passenger info:</div>
    <div>
        <el-form
        label-width="100px"
        :model="form"
        style="max-width: 460px"
        >
            <el-form-item label="First Name">
                <el-input v-model="form.firstName" />
            </el-form-item>
            <el-form-item label="Last Name">
                <el-input v-model="form.lastName" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="AddTIcket">Add ticket</el-button>
            </el-form-item>
        </el-form>
    </div>
    <el-divider></el-divider>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="firstName" label="First Name" width="180" />
        <el-table-column prop="lastName" label="Last Name" width="180" />
        <el-table-column prop="price" label="Price" />
        <el-table-column label="Options">
            <el-button type="danger">Delete</el-button>
        </el-table-column>
    </el-table>
    <el-button type="primary" class="buy_btn" @click="buyTickets">Buy tickets</el-button>

</template>
<style scoped>
.buy_btn{
    margin-top:50px;
    margin-left:100px;
}
.info{
    margin-left: 50px;
    margin-bottom: 50px;
}
</style>