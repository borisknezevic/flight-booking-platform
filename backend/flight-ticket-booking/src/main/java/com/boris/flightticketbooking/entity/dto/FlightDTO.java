package com.boris.flightticketbooking.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightDTO {

    private String airlineId;
    private String currentLocationId;

    private LocalDate departureDate;

    private LocalTime departureTime;

    private String destinationId;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private Integer ticketNum;
    private Float price;

    public FlightDTO() {
    }

    public FlightDTO(String airlineId, String currentLocationId, LocalDate departureDate, LocalTime departureTime, String destinationId, LocalDate arrivalDate, LocalTime arrivalTime, Integer ticketNum, Float price) {
        this.airlineId = airlineId;
        this.currentLocationId = currentLocationId;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destinationId = destinationId;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.ticketNum = ticketNum;
        this.price = price;
    }

    public String getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(String airlineId) {
        this.airlineId = airlineId;
    }

    public String getCurrentLocationId() {
        return currentLocationId;
    }

    public void setCurrentLocationId(String currentLocationId) {
        this.currentLocationId = currentLocationId;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "airlineId='" + airlineId + '\'' +
                ", currentLocationId='" + currentLocationId + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", destinationId='" + destinationId + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                ", ticketNum=" + ticketNum +
                ", price=" + price +
                '}';
    }
}
