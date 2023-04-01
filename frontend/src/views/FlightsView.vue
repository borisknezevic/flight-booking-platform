<script setup>
    import FlightsTable from '../components/FlightsTable.vue'
    import FlightsSearch from '../components/FlightsSearch.vue'
</script>

 <template>
    <div>
        <FlightsSearch @filteredFlights=""/>
        <FlightsTable/>
    </div>
 </template>
  