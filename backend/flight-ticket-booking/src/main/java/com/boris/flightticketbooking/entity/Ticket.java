package com.boris.flightticketbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;



@Document(collection = "tickets")
public class Ticket {
    @Id
    private String id;


    @DocumentReference(collection = "flights")
    @Field
    private Flight flight;

    @DocumentReference(collection = "user")
    @Field
    private User buyer;
    private String passengerFirstName;
    private String passengerLastName;

    public Ticket() {
    }

    public Ticket(String id, Flight flight, User buyer, String passengerFirstName, String passengerLastName) {
        this.id = id;
        this.flight = flight;
        this.buyer = buyer;
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "passengerFirstName='" + passengerFirstName + '\'' +
                ", passengerLastName='" + passengerLastName + '\'' +
                '}';
    }
}
