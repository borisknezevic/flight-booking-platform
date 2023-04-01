package com.boris.flightticketbooking.service.impl;

import com.boris.flightticketbooking.entity.Airline;
import com.boris.flightticketbooking.repository.AirlineRepository;
import com.boris.flightticketbooking.service.AirlineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineServiceImpl(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline addAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public int deleteAirline(String airlineId) {
        airlineRepository.deleteById(airlineId);
        return 1;
    }
}
