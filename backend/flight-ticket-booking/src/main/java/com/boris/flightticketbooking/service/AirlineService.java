package com.boris.flightticketbooking.service;

import com.boris.flightticketbooking.entity.Airline;

import java.util.List;

public interface AirlineService {
    List<Airline> getAllAirlines();
    Airline addAirline(Airline airline);
    int deleteAirline(String airlineId);
}
