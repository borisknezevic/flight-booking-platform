package com.boris.flightticketbooking.controller;

import com.boris.flightticketbooking.entity.Airline;
import com.boris.flightticketbooking.entity.Airport;
import com.boris.flightticketbooking.entity.Flight;
import com.boris.flightticketbooking.entity.dto.FlightDTO;
import com.boris.flightticketbooking.repository.AirlineRepository;
import com.boris.flightticketbooking.repository.AirportRepository;
import com.boris.flightticketbooking.repository.FlightRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/test")
public class TestController {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;

    public TestController(FlightRepository flightRepository, AirportRepository airportRepository, AirlineRepository airlineRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;

        this.airlineRepository = airlineRepository;
    }

    @GetMapping("/date")
    public ResponseEntity<LocalDate> getCurrentDate(){

        LocalDate localDate = LocalDate.now();
        return new ResponseEntity<>(localDate, HttpStatus.ACCEPTED);

    }

    @GetMapping("/time")
    public ResponseEntity<LocalTime> getCurrentTime(){
        LocalTime localTime = LocalTime.now();
        Clock clock = Clock.systemDefaultZone();
        LocalTime localTime1 = LocalTime.now(clock);
        return new ResponseEntity<>(localTime1, HttpStatus.ACCEPTED);
    }

    @PostMapping("/addFlights")
    public  ResponseEntity<Void> importFlights(@RequestBody List<FlightDTO> flightDTOS){
        for (FlightDTO flightDTO: flightDTOS
             ) {

            Flight newFlight = new Flight();
            //Get airline
            Airline airline = airlineRepository.findById(flightDTO.getAirlineId()).get();
            if(airline== null) throw new RuntimeException("Airport is null");
            Airport currentLocationAirport = airportRepository.findById(flightDTO.getCurrentLocationId()).get();//Get current airport
            if(currentLocationAirport == null) throw new RuntimeException("Current Location Airport is null");
            Airport destinationAirport = airportRepository.findById(flightDTO.getDestinationId()).get();
            if(destinationAirport ==  null) throw new RuntimeException("Destination airport is null");

            newFlight.setAirline(airline);
            newFlight.setCurrentLocation(currentLocationAirport);
            newFlight.setDestination(destinationAirport);


            int val = flightDTO.getDepartureDate().compareTo(flightDTO.getArrivalDate());
            if(val > 0) {
                System.out.println("");
//          The first LocalDate object is greater than the second LocalDate object");
                throw new RuntimeException("Datum polaska veci od datuma dolaska");
            } else if (val < 0){
                newFlight.setDepartureDate(flightDTO.getDepartureDate());
                newFlight.setDepartureTime(flightDTO.getDepartureTime());
                newFlight.setArrivalDate(flightDTO.getArrivalDate());
                newFlight.setArrivalTime(flightDTO.getArrivalTime());
            } else {
                if(flightDTO.getDepartureTime().compareTo(flightDTO.getArrivalTime()) >= 0) throw new RuntimeException("Vreme polaska je vece od vremene dolaska!");
                newFlight.setDepartureDate(flightDTO.getDepartureDate());
                newFlight.setDepartureTime(flightDTO.getDepartureTime());
                newFlight.setArrivalDate(flightDTO.getArrivalDate());
                newFlight.setArrivalTime(flightDTO.getArrivalTime());
            }

            newFlight.setTicketNum(flightDTO.getTicketNum());
            newFlight.setPrice(flightDTO.getPrice());
//        else if(val < 0)
//            System.out.println("");
////          The first LocalDate object is lesser than the second LocalDate object");
//        else
//            System.out.println("");
////          The LocalDate objects are equal");

            //Get destination airport
            //save flight

            flightRepository.save(newFlight);

        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/flightsByDate")
    public ResponseEntity<List<Flight>> getFlightsByDate(
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(value = "destinationId") String destinationId,
            @RequestParam(value = "currentLocationId") String currentLocationId,
            @RequestParam(value = "ticketNum") Integer ticketNum

            ){
        Airport currentLocation = airportRepository.findById(currentLocationId).get();
        Airport destination = airportRepository.findById(destinationId).get();
        List<Flight> flights = flightRepository.filterFlights(date,destination.getAirportId(),currentLocation.getAirportId(),ticketNum);

        return new ResponseEntity<>(flights, HttpStatus.ACCEPTED);
    }
}
