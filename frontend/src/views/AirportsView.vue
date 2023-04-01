<script setup>
import NewAirportForm from '../components/NewAirportForm.vue'
import {ref, onMounted} from 'vue';
import axios from 'axios';

//vars
const tableData = ref([])

//run functions when component is mounted
onMounted(()=> {fillTable();})

//adding data to the table
const fillTable = () => {
    axios
        .get('http://localhost:8080/api/v1/airports')
        .then((response) => {
            tableData.value = response.data;
        })
}

//deleting airport
const deleteAirport = () => {
    console.log("NOT WORKING!")
}


</script>
<template>
    <NewAirportForm />
    <el-divider /> 
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="airportName" label="Airport Name"/>
        <el-table-column prop="countryName" label="Country Name"/>
        <el-table-column prop="cityServed" label="City Served"/>
        
        <el-table-column label="Operation">
            <template #default="scope">
                <div>
                    <el-button type="danger" size="small" @click="deleteAirport(scope.$index, scope.row)">Delete</el-button>         
                </div>
            </template>
        </el-table-column>
    </el-table>
</template>