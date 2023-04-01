package com.boris.flightticketbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "flights")
public class Flight {

    @Id
    private String id;
    private Airline airline;

    private Airport currentLocation;
    private LocalDate departureDate;
    private LocalTime departureTime;


    private Airport destination;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;

    private Integer ticketNum;
    private Float price;
    private Float totalPrice;

//    @DocumentReference(collection = "tickets", lookup = "{ 'id' : ?#{#passengers.id} }")
    @Field
    @JsonIgnore
    private List<Ticket> passengers = new ArrayList<>();

    public Flight() {
    }

    public Flight(Flight flight) {
        this.id = flight.id;
        this.airline = flight.airline;
        this.currentLocation = flight.currentLocation;
        this.departureDate = flight.departureDate;
        this.departureTime = flight.departureTime;
        this.destination = flight.destination;
        this.arrivalDate = flight.arrivalDate;
        this.arrivalTime = flight.arrivalTime;
        this.ticketNum = flight.ticketNum;
        this.price = flight.price;
        this.totalPrice = flight.totalPrice;
        this.passengers = flight.passengers;
    }

    public Flight(String id, Airline airline, Airport currentLocation, LocalDate departureDate, LocalTime departureTime, Airport destination, LocalDate arrivalDate, LocalTime arrivalTime, Integer ticketNum, Float price, Float totalPrice, List<Ticket> passengers) {
        this.id = id;
        this.airline = airline;
        this.currentLocation = currentLocation;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.destination = destination;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.ticketNum = ticketNum;
        this.price = price;
        this.totalPrice = totalPrice;
        this.passengers = passengers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Airport currentLocation) {
        this.currentLocation = currentLocation;
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

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
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

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Ticket> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Ticket> passengers) {
        this.passengers = passengers;
    }

    public void buyTicket(Ticket ticket){
        passengers.add(ticket);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", airline=" + airline +
                ", currentLocation=" + currentLocation +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", destination=" + destination +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                ", ticketNum=" + ticketNum +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", passengers=" + passengers +
                '}';
    }
}
