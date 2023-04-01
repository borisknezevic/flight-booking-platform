package com.boris.flightticketbooking.service;

import com.boris.flightticketbooking.entity.Flight;
import com.boris.flightticketbooking.entity.dto.FlightDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightService {

    Flight createFlight(FlightDTO flightDTO);
    Boolean deleteFlight(String flightId);
    List<Flight> findFlights(Optional<LocalDate> date, Optional<String> currentLocationCity, Optional<String> destinationCity,Optional<Integer> passengerNum );
    Flight findFlightById(String flightId);
}
