package com.boris.flightticketbooking.controller;

import com.boris.flightticketbooking.entity.Airline;
import com.boris.flightticketbooking.entity.dto.AirlineDTO;
import com.boris.flightticketbooking.service.AirlineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airlines")
@CrossOrigin("http://localhost:5173")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public ResponseEntity<List<Airline>> getAllAirlines(){
        List<Airline> airlines = airlineService.getAllAirlines();
        return new ResponseEntity<>(airlines, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<String> addAirline(@RequestParam String airlineName) {
        Airline airline = new Airline(airlineName);
        Airline res = airlineService.addAirline(airline);
        return new ResponseEntity<>(res.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Integer> deleteAirline(@RequestParam String airlineId){
        int res = airlineService.deleteAirline(airlineId);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
}
