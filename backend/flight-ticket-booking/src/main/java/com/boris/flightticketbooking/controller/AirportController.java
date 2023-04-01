package com.boris.flightticketbooking.controller;

import com.boris.flightticketbooking.entity.Airport;
import com.boris.flightticketbooking.entity.dto.AirportDTO;
import com.boris.flightticketbooking.entity.dto.CityDTO;
import com.boris.flightticketbooking.service.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@RestController
@RequestMapping("/api/v1/airports")
@CrossOrigin("http://localhost:5173")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports(){
        List<Airport> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.ACCEPTED);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDTO>> getCities(){
        List<Airport> airports = airportService.getAllAirports();
        List<CityDTO> cities = airports.stream().filter(distinctByKey(Airport::getCityServed)).map(a->{
            CityDTO cityDTO = new CityDTO(a.getCityServed());
            return cityDTO;
        }).toList();
        for (CityDTO c:
             cities) {
            System.out.println(c.getCityName());
        }

        return new ResponseEntity<>(cities, HttpStatus.ACCEPTED);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }


    @PostMapping
    public ResponseEntity<Airport> addAirport(@RequestBody AirportDTO newAirport){
        Airport a = new Airport(newAirport.getCountryName(), newAirport.getAirportName(), newAirport.getCityServed());
        Airport airport = airportService.addAirport(a);
        return new ResponseEntity<>(airport, HttpStatus.CREATED);
    }
}
