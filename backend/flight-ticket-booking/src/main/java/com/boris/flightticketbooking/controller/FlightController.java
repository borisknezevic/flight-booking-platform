package com.boris.flightticketbooking.controller;

import com.boris.flightticketbooking.entity.Flight;
import com.boris.flightticketbooking.entity.dto.FlightDTO;
import com.boris.flightticketbooking.entity.dto.FlightIdDTO;
import com.boris.flightticketbooking.repository.AirlineRepository;
import com.boris.flightticketbooking.repository.AirportRepository;
import com.boris.flightticketbooking.repository.FlightRepository;
import com.boris.flightticketbooking.service.FlightService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flights")
@CrossOrigin("http://localhost:5173")
public class FlightController {

    private final FlightService flightService;
    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    public FlightController(FlightService flightService, AirlineRepository airlineRepository, AirportRepository airportRepository, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.airlineRepository = airlineRepository;
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody FlightDTO flightDTO){
        System.out.println(flightDTO);
        Flight flight = flightService.createFlight(flightDTO);
        FlightDTO response = new FlightDTO();

//        Airport airport1 = airportRepository.findAll().get(0);
//        System.out.println(airport1.getAirportName());
//        Airport airport2 = airportRepository.findAll().get(1);
//        System.out.println(airport2.getAirportName());
//        Airline airline = airlineRepository.findAll().get(0);
//        System.out.println(airline.getAirlineName());
//        Clock  clock = Clock.systemDefaultZone();
//
//        Flight flight = new Flight();
//        flight.setAirline(airline);
//        flight.setCurrentLocation(airport1);
//        flight.setDepartureDate(LocalDate.now(clock));
//        flight.setDepartureTime(LocalTime.now(clock));
//        flight.setDestination(airport2);
//        flight.setArrivalDate(LocalDate.now());
//        flight.setArrivalTime(LocalTime.now());
//        flight.setTicketNum(100);
//        flight.setPrice(10f);
//        flight.setTotalPrice(flight.getTicketNum() * flight.getPrice());
//
//        Flight ff = flightRepository.save(flight);
//
//        return new ResponseEntity<>(ff, HttpStatus.CREATED);

        return new ResponseEntity<>(flight,HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllFlights(
    ){
        List<Flight> flights = flightRepository.findAll();
        return new ResponseEntity<>(flights, HttpStatus.ACCEPTED);
    }


    @GetMapping
    public ResponseEntity<List<Flight>> getFlights(
            @RequestParam(name = "localDate", defaultValue = "2023-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> localDate,
            @RequestParam(name = "destinationCity", defaultValue = "NO_ID") Optional<String> destinationCity,
            @RequestParam(name = "currentLocationCity", defaultValue = "NO_ID") Optional<String> currentLocationCity,
            @RequestParam(name = "passengerNum", defaultValue = "1") Optional<Integer> passengerNum

    ){
        System.out.println(localDate.get().toString());
        System.out.println(destinationCity.get().toString());
        System.out.println(currentLocationCity.get().toString());
        System.out.println(passengerNum.get().toString());
        List<Flight> flights = flightService.findFlights(localDate,currentLocationCity,destinationCity,passengerNum);
        return new ResponseEntity<>(flights, HttpStatus.ACCEPTED);
    }


    @DeleteMapping
    public ResponseEntity<Boolean> deleteFlight(@RequestParam String flightId){
        System.out.println(flightId);
        Boolean deleted = flightService.deleteFlight(flightId);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlight( @PathVariable String flightId){
        Flight flight = flightService.findFlightById(flightId);
        return new ResponseEntity<>(flight,HttpStatus.FOUND);
    }

}
