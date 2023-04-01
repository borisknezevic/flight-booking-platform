<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <div class="common-layout">
      <el-container class="container">
        <el-aside class="aside" width="200px">
           <el-menu>
                <RouterLink to="/admin/flights" replace style="text-decoration: none; color: inherit;">
                    <el-menu-item index="1">
                        <el-icon><Promotion /></el-icon>
                        <span>Flights</span>
                    </el-menu-item>
                </RouterLink>
                <RouterLink to="/admin/newFlight" replace style="text-decoration: none; color: inherit;">
                    <el-menu-item index="3">
                        <el-icon><Position /></el-icon>
                        <span>Add Flight</span>
                    </el-menu-item>
                </RouterLink>
                <RouterLink to="/admin/airlines" replace style="text-decoration: none; color: inherit;">
                    <el-menu-item index="2">
                        <el-icon><document /></el-icon>
                        <span>Airlines</span>
                    </el-menu-item>
                </RouterLink>
                <RouterLink to="/admin/airports" replace style="text-decoration: none; color: inherit;">
                    <el-menu-item index="3">
                        <el-icon><Location /></el-icon>
                        <span>Airports</span>
                    </el-menu-item>
                </RouterLink>
            
            </el-menu>
        </el-aside>
        <el-main class="main">
            <RouterView />
        </el-main>
      </el-container>
    </div>
</template>

<style scoped>


.container{
    height: 90vh;
}

</style>