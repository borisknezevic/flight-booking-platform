<script setup>
    import SearchDate from '../components/FlightsSearch/SearchDate.vue'
    import SearchDestination from '../components/FlightsSearch/SearchDestination.vue'
    import SearchLocation from '../components/FlightsSearch/SearchLocation.vue'
    import SearchTicketNumber from '../components/FlightsSearch/SearchTicketNumber.vue'
    import SearchButton from '../components/FlightsSearch/SearchButton.vue'

    import {reactive} from 'vue'
    import axios from 'axios'
    import {useFlightsStore} from '../stores/flights'
    
    const store = useFlightsStore();




    const searchBody =  reactive({
        date:'',
        destinationCity:'',
        currentLocationCity:'',
        passengerNum: 0
    })

    const searchParamDate = (date) =>{
        searchBody.date = date
    }
    const searchParamCurrentLocation = (currentLocation) => {
        searchBody.currentLocationCity = currentLocation
    } 
    const searchParamDestination = (destination) => {
        console.log(destination)
        searchBody.destinationCity = destination
    }
    const searchParamPassengerNum = (passengerNum) => {
        searchBody.passengerNum=  passengerNum
        // console.log(searchBody.date)
        // console.log(searchBody.currentLocationCity)
        // console.log(searchBody.destinationCity)
        // console.log(searchBody.passengerNum)
    }

    const submit = () => {
        console.log("submit test")
        axios
            .get('http://localhost:8080/api/v1/flights',{
                params:{
                    localDate: searchBody.date,
                    destinationCity : searchBody.destinationCity,
                    currentLocationCity :searchBody.currentLocationCity,
                    passengerNum : searchBody.passengerNum
                }
            })
            .then((response) => {
                console.log(response.data)
                store.setFlights(response.data)
                
            })
    }

    

</script>

<template>
    <el-row class="row" :gutter="20" justify="space-evenly">
        <el-col :span="4">
            <div class="grid-content ep-bg-purple">
                <SearchDate 
                    @searchParameterDate = "searchParamDate"
                />
            </div>
        </el-col>
        <el-col :span="4">
            <div class="grid-content ep-bg-purple">
                <SearchLocation 
                    @searchParameterCurrentLocation = "searchParamCurrentLocation"
                />
            </div>
        </el-col>
        <el-col :span="4">
            <div class="grid-content ep-bg-purple">
                <SearchDestination 
                @searchParameterDestination = "searchParamDestination"
                />
            </div>
        </el-col>
        <el-col :span="4">
            <div class="grid-content ep-bg-purple">
                <SearchTicketNumber
                @searchParameterPassengerNum = "searchParamPassengerNum"
                />
            </div>
        </el-col>
        <el-col :span="4" class="center">
            <div class="grid-content ep-bg-purple">
                <el-button type="primary" @click="submit" >Search</el-button>
            </div>
        </el-col>
    </el-row>
  </template>
  
<style scoped>
    .center{
        display: flex;
        justify-content: center;
        align-self:center;
    }

  .el-row {
    margin-bottom: 20px;
  }
  .el-row:last-child {
    margin-bottom: 0;
  }
  .el-col {
    border-radius: 4px;
  }
  
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
    /* background-color: aquamarine; */
  }

  .row{
    border: 5px solid var(--el-box-shadow-dark);
    background-color: aliceblue;
  }
</style>
  