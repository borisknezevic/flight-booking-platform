<script setup>
import {ref} from 'vue';
import axios from 'axios';
import {useUsersStore} from '../stores/user'
import router from '../router'
import { storeToRefs } from 'pinia';

const store  = useUsersStore()


const email = ref('');
const password = ref('');

const login = () => {

  axios
    .post('http://localhost:8080/api/auth/login',{
      email: email.value,
      password: password.value
    })
    .then((response) => {
      if(response.status >= 200){
        store.setAccessToken(response.data.accessToken)
        store.setRefreshToken(response.data.refreshToken)
        store.setUserId(response.data.userId)
        store.setUserRole(response.data.role)
        store.setFirstName(response.data.firstName)
        store.setLastName(response.data.lastName)
        console.log(response.data)
        if(response.data.role === 'USER'){
          console.log(response.data.role)
          router.push('/flights')
        } else {
          router.push('/admin/flights')
        } 
      }
    })
    .catch(function(error) {
    console.log('Error on Authentication');
    });
}


</script>
<template>
  <el-row>
    <el-col :span="9"></el-col>
    <el-col :span="6">
      <el-form class="top">
        <el-form-item label="Email">
          <el-input v-model="email" />
        </el-form-item>
        <el-form-item label="Password">
          <el-input v-model="password" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login">Login</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="9"></el-col>
  </el-row>
</template>

<style scoped>
.top{
  margin-top:150px
}
</style>