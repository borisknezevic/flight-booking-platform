package com.boris.flightticketbooking.service.impl;

import com.boris.flightticketbooking.entity.Airport;
import com.boris.flightticketbooking.repository.AirportRepository;
import com.boris.flightticketbooking.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport addAirport(Airport airport) {
        return airportRepository.save(airport);
    }
}
