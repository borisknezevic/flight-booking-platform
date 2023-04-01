package com.boris.flightticketbooking.service;

import com.boris.flightticketbooking.entity.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> getAllAirports();
    Airport addAirport(Airport airport);
}
