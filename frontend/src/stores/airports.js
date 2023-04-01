import { ref, computed,reactive } from 'vue'
import { defineStore } from 'pinia'


export const useAirportsStore = defineStore('airports', () => {
    
    //Departure
    const airports = ref([])
    const departureCountry = ref('')
    const departureCity = ref('')
    const departureAirport = reactive({
        airportName:'',
        airportId:''
    })

    //Destination
    const destinationCountry = ref('')
    const destinationCity = ref('')
    const destinationAirport = reactive({
        airportName:'',
        airportId:''
    })

    const getAirports = computed(() => airports.value)

    //Departure getters
    const getCountries = computed(() => {
        const countries = airports.value.map(airport => airport.countryName)
        const distinctCounties = [... new Set(countries)]
        return distinctCounties
    })

    const getCities = computed(() => {
        const cities = airports.value.filter(airport => airport.countryName == departureCountry.value ).map(airport => airport.cityServed)
        const distinctCities = [... new Set(cities)]
        console.log("PINIA" + distinctCities)
        return distinctCities
    })

    const getDepartureAirports = computed(() => {
        const dAirports = airports.value.filter(airport => airport.cityServed == departureCity.value).map(airport => ({
            airportName: airport.airportName,
            airportId: airport.airportId
        }))
        console.log("Aerodromi polaska:" + dAirports)
        return dAirports
    })

    //Destination getters
    const getDestinationCountries = computed(() => {
        const countries = airports.value.map(airport => airport.countryName)
        const distinctCounties = [... new Set(countries)]
        return distinctCounties
    })

    const getDestinationCities = computed(() => {
        const cities = airports.value.filter(airport => airport.countryName == destinationCountry.value ).map(airport => airport.cityServed)
        const distinctCities = [... new Set(cities)]
        console.log("PINIA" + distinctCities)
        return distinctCities
    })

    const getDestinationAirports = computed(() => {
        const dAirports = airports.value.filter(airport => airport.cityServed == destinationCity.value).map(airport => ({
            airportName: airport.airportName,
            airportId: airport.airportId
        }))
        console.log("Aerodromi dolaska:" + dAirports)
        return dAirports
    })

    
    function setAirports(data) {
        airports.value = data
    }
    
    //Departure functions
    function setDepartureCountry(data){
        departureCountry.value = data
    }

    function setDepartureCity(data){
        departureCity.value = data
    }

    function setDepartureAirport(data){
        departureAirport.value = data
    }

    //Destination functions
    function setDestinationCountry(data){
        destinationCountry.value = data
    }

    function setDestinationCity(data){
        destinationCity.value = data
    }

    function setDestinationAirport(data){
        destinationAirport.value = data
    }

    return { 
        departureCountry, 
        airports, getAirports, 
        getCountries,getCities,
        getDepartureAirports,setAirports, 
        setDepartureCountry,setDepartureCity,
        setDepartureAirport,
        setDestinationCountry,setDestinationCity,setDestinationAirport,
        getDestinationCountries,getDestinationCities,getDestinationAirports,
        destinationCountry,destinationCity,destinationAirport
    }
})