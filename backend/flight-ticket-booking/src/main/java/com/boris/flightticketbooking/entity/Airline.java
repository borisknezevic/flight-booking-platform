package com.boris.flightticketbooking.entity;

import com.boris.flightticketbooking.entity.dto.AirlineDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;




@Document(collection = "airlines")
public class Airline {

    @Id
    private String id;
    private String airlineName;

    public Airline() {
    }

    public Airline(AirlineDTO airlineDTO){
        this.airlineName = airlineDTO.getAirlineName();
    }

    public Airline(String airlineName) {
        this.airlineName = airlineName;
    }

    public Airline(String id, String airlineName) {
        this.id = id;
        this.airlineName = airlineName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
}
