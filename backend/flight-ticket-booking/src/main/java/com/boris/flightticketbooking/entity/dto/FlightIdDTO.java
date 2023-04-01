package com.boris.flightticketbooking.entity.dto;

public class FlightIdDTO {
    private String flightId;

    public FlightIdDTO() {
    }

    public FlightIdDTO(String flightId) {
        this.flightId = flightId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
}
