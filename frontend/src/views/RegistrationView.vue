<script setup>
import {ref,reactive } from 'vue'
import router from '../router'
import axios from 'axios'


const validPassword = ref('')
// do not use same name with ref
const form = reactive({
  firstName: '',
  lastName: '',
  email: '',
  password: ''
})

const onSubmit = () => {

  validation();
  axios
    .post('http://localhost:8080/api/auth/signup',
    {
      email: form.email,
      password: form.password,
      firstName: form.firstName,
      lastName: form.lastName
    })
    .then((results) => {
      console.log(results.data);
      redirectToLoginPage()
    })
}

const validation = () => {
  console.log("Validation")
}

const redirectToLoginPage = () => {
  console.log("Redirect From: Registration page To: Login page")
  router.push("/login")
}

</script>



<template>
  <el-row :gutter="20">
    <el-col :span="8"></el-col>
    <el-col :span="8" class="form">
      <el-form :model="form" label-width="120px">
        <el-form-item label="First Name">
          <el-input v-model="form.firstName"/>
        </el-form-item>
        <el-form-item label="Last Name">
          <el-input v-model="form.lastName" />
        </el-form-item>
        <el-form-item label="Email">
          <el-input v-model="form.email" />
        </el-form-item>
        <el-form-item label="Password">
          <el-input v-model="form.password" type="password" />
        </el-form-item>
        <el-form-item label="Password">
          <el-input v-model="validPassword" type="password" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">Register</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="8"></el-col>
  </el-row>




 
</template>

<style scoped>
.form{
  margin-top:150px;
}
</style>