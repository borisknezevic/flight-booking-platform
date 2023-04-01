package com.boris.flightticketbooking.repository;

import com.boris.flightticketbooking.entity.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends MongoRepository<Airport, String> {

}
