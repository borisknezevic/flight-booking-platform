<script setup>
import { RouterLink, RouterView } from 'vue-router'
import router from './router';
import axios from 'axios';

// import HelloWorld from './components/HelloWorld.vue'
import { useUsersStore } from './stores/user';

const store = useUsersStore()

const handleLogout = () => {
  console.log("LOGOUT")
  console.log(store.getAccessToken)
  console.log(store.getRefreshToken)
  axios
    .post('http://localhost:8080/api/auth/logout',
    {
      refreshToken: store.getRefreshToken
    })
    .then((response) => {console.log(response)
      store.userLogout()
      router.push('/flights')
    })   
}
</script>

<template>
  <main>
   <header>
      <!-- ADMIN -->
      <div class="d-flex flex-row-reverse mb-6 bg-surface-variant" v-if="store.getUserRole == 'ADMIN'">
        <div  style="text-decoration: none; color: inherit;" >
          <el-button class="ma-2 pa-2" type="success" @click="handleLogout" >Logout</el-button>
        </div>
        <RouterLink to="/admin" style="text-decoration: none; color: inherit;">
          <v-btn class="ma-2 pa-2" variant="flat">Admin</v-btn>
        </RouterLink>
      </div>
      <!-- USER -->
      <div class="d-flex flex-row-reverse mb-6 bg-surface-variant" v-if="store.getUserRole == 'USER'">
        <RouterLink to="/flights" style="text-decoration: none; color: inherit;">
          <v-btn class="ma-2 pa-2" variant="flat" @click="handleLogout">Logout</v-btn>
        </RouterLink>
        <RouterLink to="/flights" style="text-decoration: none; color: inherit;">
          <v-btn class="ma-2 pa-2" variant="flat">Flights</v-btn>
        </RouterLink>
        <RouterLink to="/profile" style="text-decoration: none; color: inherit;">
          <v-btn class="ma-2 pa-2" variant="flat">Profile</v-btn>
        </RouterLink>
        <!-- TREBA IZBRISATI! -->
        <!-- <RouterLink to="/ticketBuying" style="text-decoration: none; color: inherit;">
          <v-btn class="ma-2 pa-2" variant="flat">Ticket Buying</v-btn>
        </RouterLink> -->
      </div>
      <!-- UNDEFINED -->
      <div class="d-flex flex-row-reverse mb-6 bg-surface-variant" v-if="store.getUserRole == 'UNDEFINED'">
        <RouterLink to="/registration" style="text-decoration: none; color: inherit;">
          <v-btn class="ma-2 pa-2" variant="flat">Registration</v-btn>
        </RouterLink>
        <RouterLink to="/login" style="text-decoration: none; color: inherit;">
          <v-btn class="ma-2 pa-2" variant="flat" color="secondary">Login</v-btn>
        </RouterLink>
        <RouterLink to="/flights" style="text-decoration: none; color: inherit;">
          <v-btn class="ma-2 pa-2" variant="flat">Flights</v-btn>
        </RouterLink>
      </div>
   </header>
   <main>
    <RouterView />
   </main>
  </main>
  <!-- <header>
    <div>
 

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header>
  <main>
    <p>TEST</p>
  </main>
  <RouterView /> -->
</template>

<style scoped>

</style>
