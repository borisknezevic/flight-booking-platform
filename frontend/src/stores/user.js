import { ref, computed,watch } from 'vue'
import { defineStore } from 'pinia'


export const useUsersStore = defineStore('users', () => {
  const accessToken = ref('')
  const refreshToken = ref('')
  const userId = ref('')
  const userRole = ref('UNDEFINED')
  const userFirstName = ref('')
  const userLastName = ref('')

  const getAccessToken = computed(() => accessToken.value)
  const getRefreshToken= computed(() => refreshToken.value)
  const getUserId = computed(() => userId.value)
  const getUserRole = computed(() => userRole.value)
  const getFirstName = computed(() => userFirstName.value)
  const getLastName = computed(() => userLastName.value)


  function setAccessToken(token) {
    accessToken.value = token
  }
  function setRefreshToken(token) {
    refreshToken.value = token
  }
  function setUserId(id) {
    userId.value = id
  }
  function setUserRole(role) {
    userRole.value = role
  }
  function setFirstName(firstName){
    userFirstName.value = firstName
  }
  function setLastName(lastName){
    userLastName.value = lastName
  }

  function userLogout(){

    accessToken.value = '',
    refreshToken.value = '',
    userId.value = '',
    userRole.value = 'UNDEFINED'
    userFirstName.value = ''
    userLastName.value = ''

    localStorage.removeItem("accessToken")
    localStorage.removeItem("refreshToken")
    localStorage.removeItem("userId")
    localStorage.removeItem("userRole")
    localStorage.removeItem("userFirstName")
    localStorage.removeItem("userLastName")
  }

  if(localStorage.getItem("accessToken")){ accessToken.value = JSON.parse(localStorage.getItem("accessToken"))}
  if(localStorage.getItem("refreshToken")){ refreshToken.value = JSON.parse(localStorage.getItem("refreshToken"))}
  if(localStorage.getItem("userId")){ userId.value = JSON.parse(localStorage.getItem("userId"))}
  if(localStorage.getItem("userRole")){ userRole.value = JSON.parse(localStorage.getItem("userRole"))}
  if(localStorage.getItem("userFirstName")){ userFirstName.value = JSON.parse(localStorage.getItem("userFirstName"))}
  if(localStorage.getItem("userLastName")){ userLastName.value = JSON.parse(localStorage.getItem("userLastName"))}


  watch(accessToken,(accessToken) => {localStorage.setItem("accessToken",JSON.stringify(accessToken))},{deep:true})
  watch(refreshToken,(refreshToken) => {localStorage.setItem("refreshToken",JSON.stringify(refreshToken))},{deep:true})
  watch(userId,(userId) => {localStorage.setItem("userId",JSON.stringify(userId))},{deep:true})
  watch(userRole,(userRole) => {localStorage.setItem("userRole",JSON.stringify(userRole))},{deep:true})
  watch(userFirstName,(userFirstName) => {localStorage.setItem("userFirstName",JSON.stringify(userFirstName))},{deep:true})
  watch(userLastName,(userLastName) => {localStorage.setItem("userLastName",JSON.stringify(userLastName))},{deep:true})



  return { accessToken, refreshToken, userId ,userRole,getAccessToken,getRefreshToken,getUserId,
         getUserRole,setAccessToken,setRefreshToken,setUserId,setUserRole,userLogout,
         getFirstName,getLastName,setFirstName,setLastName,userFirstName,userLastName
        }
})