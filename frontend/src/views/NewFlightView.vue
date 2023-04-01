<script setup>
import {ref ,onMounted} from 'vue';
import {useAirportsStore} from '../stores/airports'
import {useAirlinesStore} from '../stores/airlines'
import {useUsersStore} from '../stores/user'
import axios from 'axios';

const userStore = useUsersStore();
const store = useAirportsStore();
const airlineStore = useAirlinesStore()

const success = ref('')

onMounted(() => {
    axios
    .get('http://localhost:8080/api/v1/airports')
    .then((response) => {
        store.setAirports(response.data);
        countryOptions.value = store.getCountries
        destinationCountryOptions.value = store.getCountries
    })

    axios
    .get('http://localhost:8080/api/v1/airlines')
    .then((response) => {
        airlineStore.setAirlines(response.data)
        airlineOptions.value = airlineStore.getAirlines
    })
})

const createFlight = () => {
    let newFlight = {
        airlineId: airline.value,
        currentLocationId: departureAirport.value,
        departureDate: departureDate.value,
        departureTime: departureTime.value,
        destinationId: destinationAirport.value,
        arrivalDate: arrivalDate.value,
        arrivalTime: arrivalTime.value,
        ticketNum: ticketNumber.value,
        price: ticketPrice.value,
    }
    console.log(newFlight);
    axios
        .post('http://localhost:8080/api/v1/flights',newFlight,{
        headers:{
            Authorization: "Bearer " + userStore.getAccessToken
        }})
        .then((response) => {
            if(response.status >= 200) {
                console.log("STATUS 2")
                success.value = "Flight successfully added to database"
            }
        })
}

//Departure
const departureCountry = ref([])
const countryOptions = ref([])

const departureCity = ref([])
const departureCityOptions = ref([])

const departureAirport = ref([])
const departureAirportsOptions = ref([])

const departureDate = ref('')
const departureTime = ref('')

//Destination
const destinationCountry = ref([])
const destinationCountryOptions = ref([])

const destinationCity = ref([])
const destinationCityOptions = ref([])

const destinationAirport = ref([])
const destinationAirportsOptions = ref([])

const arrivalDate = ref('')
const arrivalTime = ref('')

//Other

const airline = ref([])
const airlineOptions = ref([])

const ticketNumber = ref(0)
const ticketPrice = ref(0)


// do not use same name with ref
let newFlight = {
  airlineId: airline.value.id,
  currentLocationId: departureAirport.value,
  departureDate: departureDate.value,
  departureTime: departureTime.value,
  destinationId: destinationAirport.value,
  arrivalDate: arrivalDate.value,
  arrivalTime: arrivalTime.value,
  ticketNum: ticketNumber.value,
  price: ticketPrice.value,
}


//Departure
const updateDepartureCities = () => {
    console.log("Izabrana drzava" + departureCountry.value)
    console.log("Postavi izabranu drzavu na store")
    store.setDepartureCountry(departureCountry.value)
    console.log("Dobavi gradove za izabranu drzavu")
    console.log(store.getCities)
    departureCityOptions.value = store.getCities
}

const updateDepartureAirports = () => {
    console.log("Izabrabni grad" + departureCity.value)
    console.log("Postavi izabrani grad na store")
    store.setDepartureCity(departureCity.value)
    console.log("Dobavi aerodrome za izabrani grad")
    console.log(store.getDepartureAirports)
    departureAirportsOptions.value = store.getDepartureAirports
}

//Destination
const updateDestinationCities = () => {
    console.log("Izabrana drzava" + destinationCountry.value)
    console.log("Postavi izabranu drzavu na store")
    store.setDestinationCountry(destinationCountry.value)
    console.log("Dobavi gradove za izabranu drzavu")
    console.log(store.getDestinationCities)
    destinationCityOptions.value = store.getDestinationCities
}

const updateDestinationAirports = () => {
    console.log("Izabrabni grad" + destinationCity.value)
    console.log("Postavi izabrani grad na store")
    store.setDestinationCity(destinationCity.value)
    console.log("Dobavi aerodrome za izabrani grad")
    console.log(store.getDestinationAirports)
    destinationAirportsOptions.value = store.getDestinationAirports
}


const onSubmit = () => {
    console.log('submit!')
    createFlight();
}
</script>
<template>
    <el-form>
    <el-row :gutter="20">
        <el-col :span="11">
                <el-form-item label="Country:">
                    <el-select v-model="departureCountry"
                         filterable
                         placeholder="Select"
                         @change="updateDepartureCities"
                         >
                        <el-option
                            v-for="country in countryOptions"
                            :key="country"
                            :label="country"
                            :value="country"

                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="City:">
                    <el-select 
                        v-model="departureCity" 
                        filterable placeholder="Select" 
                        @change="updateDepartureAirports"
                        >
                        <el-option
                            v-for="city in departureCityOptions"
                            :key="city"
                            :label="city"
                            :value="city"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="Airport:">
                    <el-select v-model="departureAirport" filterable placeholder="Select">
                        <el-option
                            v-for="airport in departureAirportsOptions"
                            :key="airport.airportId"
                            :label="airport.airportName"
                            :value="airport.airportId"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="Departure date:">
                    <div class="demo-date-picker">
                        <div class="block">
                            <el-date-picker
                                v-model="departureDate"
                                type="date"
                                placeholder="Select date"
                                value-format="YYYY-MM-DD"
                            />
                        </div>
                    </div>
                </el-form-item>
                <el-form-item label="Departure time:">
                    <el-time-picker 
                        v-model="departureTime" 
                        placeholder="Arbitrary time"
                        value-format="HH:mm"
                        />
                </el-form-item>
        </el-col>
        <el-col :span="2">
            <el-divider direction="vertical" class="divider" />
        </el-col>
        <el-col :span="11">
                <el-form-item label="Country:">
                    <el-select 
                        v-model="destinationCountry" 
                        filterable 
                        placeholder="Select"
                        @change="updateDestinationCities"
                        >
                        <el-option
                            v-for="country in destinationCountryOptions"
                            :key="country"
                            :label="country"
                            :value="country"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="City:">
                    <el-select 
                        v-model="destinationCity" 
                        filterable 
                        placeholder="Select"
                        @change="updateDestinationAirports"
                        >
                        <el-option
                            v-for="city in destinationCityOptions"
                            :key="city"
                            :label="city"
                            :value="city"
                            
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="Airport:">
                    <el-select v-model="destinationAirport" filterable placeholder="Select">
                        <el-option
                            v-for="airport in destinationAirportsOptions"
                            :key="airport.airportId"
                            :label="airport.airportName"
                            :value="airport.airportId"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="Arrival date:">
                    <div class="demo-date-picker">
                        <div class="block">
                            <el-date-picker
                                v-model="arrivalDate"
                                type="date"
                                placeholder="Select date"
                                value-format="YYYY-MM-DD"
                            />
                        </div>
                    </div>
                </el-form-item>
                <el-form-item label="Arrival time:">
                    <el-time-picker 
                        v-model="arrivalTime" 
                        placeholder="Arbitrary time"
                        value-format="HH:mm"
                        />
                </el-form-item>
        </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
        <el-col :span="12">
                <el-form-item label="Airline:">
                    <el-select v-model="airline"
                         filterable
                         placeholder="Select"
                         >
                        <el-option
                            v-for="a in airlineOptions"
                            :key="a.id"
                            :label="a.airlineName"
                            :value="a.id"

                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="Tickets number:">
                    <el-input v-model="ticketNumber" placeholder="Please input" />
                </el-form-item>
                <el-form-item label="Price for one ticket($):">
                    <el-input v-model="ticketPrice" placeholder="Please input" />
                </el-form-item>
                <el-form-item>
                    <el-button 
                        type="primary"
                        @click="onSubmit"
                        >Add new flight</el-button>
                </el-form-item>
        </el-col>
    </el-row>
</el-form>

<el-text class="mx-1" type="success">{{success}}</el-text>

</template>

<style scoped>
    .divider{
        height: 100%;
    }

</style>