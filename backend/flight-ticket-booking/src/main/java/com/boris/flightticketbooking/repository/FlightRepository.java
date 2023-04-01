package com.boris.flightticketbooking.repository;

import com.boris.flightticketbooking.entity.Airport;
import com.boris.flightticketbooking.entity.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {

//    @Query("{$and: [{departureDate: },{}]}")
//    @Query("{'departureDate': ?0 }")
    @Query("{$and:[{'departureDate': ?0 },{'destination.cityServed':  ?1},{'currentLocation.cityServed' : ?2},{'ticketNum' : {$gte : ?3 }}]}")
    List<Flight> filterFlights(LocalDate localDate, String destination, String currentLocation,Integer ticketNum);

    @Query("{$and:[{'currentLocation.cityServed':  ?0},{'destination.cityServed' : ?1}]}")
    List<Flight> filterFlightsTwo(String currentLocation, String destination);
}
