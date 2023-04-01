package com.boris.flightticketbooking.service.impl;

import com.boris.flightticketbooking.entity.Airline;
import com.boris.flightticketbooking.entity.Airport;
import com.boris.flightticketbooking.entity.Flight;
import com.boris.flightticketbooking.entity.dto.FlightDTO;
import com.boris.flightticketbooking.repository.AirlineRepository;
import com.boris.flightticketbooking.repository.AirportRepository;
import com.boris.flightticketbooking.repository.FlightRepository;
import com.boris.flightticketbooking.service.FlightService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;

    public FlightServiceImpl(FlightRepository flightRepository, AirportRepository airportRepository, AirlineRepository airlineRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
    }

    @Override
    public Flight createFlight(FlightDTO flightDTO) {

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

        return flightRepository.save(newFlight);
    }

    @Override
    public Boolean deleteFlight(String flightId) {
        Flight flight = flightRepository.findById(flightId).get();
        flightRepository.delete(flight);
        return true;
    }

    @Override
    public List<Flight> findFlights(Optional<LocalDate> date, Optional<String> currentLocationCity, Optional<String> destinationCity, Optional<Integer> passengerNum) {
//        Airport currentLocation = airportRepository.findById(currentLocationId.get()).get();
//        Airport destination = airportRepository.findById(destinationId.get()).get();
//        List<Flight> flights = flightRepository.filterFlights(date.get(),"Vienna","Minsk",passengerNum.get());
        List<Flight> flights = flightRepository.filterFlights(date.get(),destinationCity.get(),currentLocationCity.get(),passengerNum.get());

        System.out.println(flights.size());
        return flights;
    }

    @Override
    public Flight findFlightById(String flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        return flight.map(Flight::new).orElseThrow( () -> new RuntimeException("Flight with id: " + flightId  + " not found!"));
    }
}
