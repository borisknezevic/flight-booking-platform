<script setup>
import axios from 'axios';
import {useUsersStore} from '../stores/user'
import {reactive} from 'vue';

//stores
const userStore = useUsersStore();

const form = reactive({
    countryName: '',
    cityServed: '',
    airportName: ''
});

//adding new airport
const onSubmit = () => {
    axios
        .post('http://localhost:8080/api/v1/airports',
        {...form},
        {headers: {
            Authorization: "Bearer " + userStore.getAccessToken
        }})
        .then((response) => {
            console.log(response)
        })
}

</script>

<template>
      <el-col :span="8">
    <el-form :model="form" label-width="120px">
        <el-form-item label="Country Name">
            <el-input v-model="form.countryName" />
        </el-form-item>
        <el-form-item label="City Served">
            <el-input v-model="form.cityServed" />
        </el-form-item>
        <el-form-item label="Airport Name">
            <el-input v-model="form.airportName" />
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">Add new airport</el-button>
        </el-form-item>
    </el-form>
</el-col>

</template>