package com.boris.flightticketbooking.entity.dto;

import com.boris.flightticketbooking.entity.Airline;

public class AirlineDTO {

    private String airlineName;

    public AirlineDTO() {
    }

    public AirlineDTO(Airline airline){
        this.airlineName = airline.getAirlineName();
    }
    public AirlineDTO(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
}
