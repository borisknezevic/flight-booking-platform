package com.boris.flightticketbooking.repository;

import com.boris.flightticketbooking.entity.Airline;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends MongoRepository<Airline, String> {

}
