<script setup>
import {ref,reactive,onMounted} from 'vue';
import {useUsersStore} from '../stores/user'
import axios from 'axios';

//stores
const userStore = useUsersStore()

//vars
const form = reactive({
    airlineName: ''
});
const tableData = ref([])

//run function on component mounted
onMounted(() => { fillTable(); })

//adding new airline
const onSubmit = () => {
    console.log(userStore.getAccessToken)
    axios
    .post('http://localhost:8080/api/v1/airlines',{},{
            headers:{
                Authorization: "Bearer " + userStore.getAccessToken
            },
            params:{
                airlineName: form.airlineName
            }})
    .then((response) => {
        if(response.status >= 200){
            console.log(response.data)
            tableData.value.push({
                airlineId: response.data,
                airlineName: form.airlineName
            })
        }
    })
}
//adding data to the table
const fillTable = () => {
    axios
    .get('http://localhost:8080/api/v1/airlines')
    .then((response) => {
        console.log(response.data)
        tableData.value = response.data
    })

}
//deleting airline and update the table
const deleteAirline = (index, proxyAirline) => {

    const rawAirline = {...proxyAirline}

    axios
      .delete('http://localhost:8080/api/v1/airlines',{
        headers: {
          Authorization: "Bearer " + userStore.getAccessToken
        },params:{
            airlineId: rawAirline.id
        }})
      .then((response) => {
            if(response.data==1)
                tableData.value.pop({airlineId: rawAirline.id})
        })


}

</script>

<template>
    {{ form.airlineName }}
    <el-col :span="8">
        <el-form :model="form" label-width="120px">
            <el-form-item label="Airline Name">
                <el-input v-model="form.airlineName" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">Add new airline</el-button>
            </el-form-item>
        </el-form>
    </el-col>
    <el-divider />
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="airlineName" label="Airline Name"/>
        <el-table-column label="Operation">
            <template #default="scope">
                <div>
                    <el-button type="danger" size="small" @click="deleteAirline(scope.$index, scope.row)">Delete</el-button>
                </div>
            </template>
        </el-table-column>
    </el-table>
</template>