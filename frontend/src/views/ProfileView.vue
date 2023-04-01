<script setup>
import {ref,onMounted} from 'vue';
import axios from 'axios';
import {useProfileStore} from '../stores/profile'
import {useUsersStore} from '../stores/user'

const profileStore = useProfileStore()
const userStore = useUsersStore()

const data = ref()

onMounted(() => {
    axios
        .get('http://localhost:8080/api/v1/users/tickets',{
            headers:{
                Authorization: "Bearer " + userStore.getAccessToken
            }
        })
        .then((response) => {
            console.log(response)
            data.value = response.data
        })
})
 

</script>
<template>
    <div>
        <v-card-title class="text-h3 text-md-h5 text-lg-h4">{{userStore.getFirstName}} {{ userStore.getLastName }}</v-card-title>
        <v-divider></v-divider>
    </div>
    <el-table :data="data" style="width: 100%">
        <el-table-column prop="departureDate" label="Date" />
        <el-table-column prop="departureCity" label="From:" />
        <el-table-column prop="destinationCity" label="To:" />
        <el-table-column prop="passengerFirstName" label="First Name" />
        <el-table-column prop="passengerLastName" label="Last Name" />
        <el-table-column prop="price" label="Price" />
        <el-table-column label="">
            <template #default>
                <el-button type="success" size="small"
                    >Details</el-button
                >
            </template>
        </el-table-column>
        
    </el-table>
</template>
<style scoped>
</style>