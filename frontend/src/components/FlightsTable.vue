<script setup>
import axios from 'axios';
import {ref,watchEffect} from 'vue'
import { useFlightsStore } from '../stores/flights';
import { storeToRefs } from 'pinia'
import { useTicketsStore } from '../stores/tickets';
import {useUsersStore} from '../stores/user'
import router from '../router'
const ticketStore = useTicketsStore();
const store = useFlightsStore();
const userStore = useUsersStore();
const  {flights,getFlights} = storeToRefs(store)

    const tableData = ref('')

    // axios.get('http://localhost:8080/api/v1/flights')
    // .then(response => {
    //     // displayData(response.data);
    //     console.log(response.data)});

tableData
  const handleDelete = (index,proxyFlight) => {
    const rawFlight = {...proxyFlight}

    axios
      .delete('http://localhost:8080/api/v1/flights',{
        headers: {
          Authorization: "Bearer " + userStore.getAccessToken
        },params:{
          flightId: rawFlight.flightId
        }})
      .then((response) => console.log(response))
    
    } 
    
  const handleBuy = (index,proxyFlight) => {
    const rawFlight = {...proxyFlight}
    ticketStore.setFlight(rawFlight)
    router.push("/ticketBuying")
  }



  watchEffect(() => {

        if(store.getFlightSelected){
        tableData.value = flights.value.map((f) => ({
            flightId: f.id,
            departureDate: f.departureDate,
            departureTime: f.departureTime.slice(0,-3),
            airlineName : f.airline.airlineName,
            currentLocation: f.currentLocation.countryName,
            currentAirport: f.currentLocation.airportName,
            currentCity: f.currentLocation.cityServed,
            arrivalDate: f.arrivalDate,
            arrivalTime: f.arrivalTime.slice(0,-3),
            destinationState:f.destination.countryName,
            destinationAirport:f.destination.airportName,
            destinationCity:f.destination.cityServed,
            price: f.price + '$',
            ticketNum:f.ticketNum
        })) 
        }
      })
    


 </script>

<template>
    <el-table v-model="tableData" :data="tableData" style="width: 100%">
      <el-table-column fixed prop="departureDate" label="Date" width="150" />
      <el-table-column prop="departureTime" label="Time" width="60" />
      <el-table-column prop="airlineName" label="Airline" width="120" />
      <el-table-column prop="currentLocation" label="From" width="120" />
      <el-table-column prop="currentAirport" label="Airport" width="120" />
      <el-table-column prop="currentCity" label="City" width="120" />
      <el-table-column prop="arrivalDate" label="Date" width="150" />
      <el-table-column prop="arrivalTime" label="Time" width="150" />
      <el-table-column prop="destinationState" label="Destination" width="120" />
      <el-table-column prop="destinationAirport" label="Airport" width="120" />
      <el-table-column prop="destinationCity" label="City" width="120" />
      <el-table-column prop="price" label="Price" width="120" />
      <el-table-column prop="ticketNum" label="Tickets left" width="120" />
      <el-table-column fixed="right" label="Operations" width="120">
        <template #default="scope">

          <!-- ADMIN -->
          <div v-if="userStore.getUserRole == 'ADMIN'">
            <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >Delete</el-button
            >
          </div>
          <!-- USER -->
          <div v-if="userStore.getUserRole == 'USER'">
            <el-button 
            size="small" 
            @click="handleBuy(scope.$index, scope.row)"
            >
            Buy
            </el-button>
          </div>
      </template>
      </el-table-column>
    </el-table>
</template>

  