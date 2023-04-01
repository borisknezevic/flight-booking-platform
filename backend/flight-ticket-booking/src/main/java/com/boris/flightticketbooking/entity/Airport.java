package com.boris.flightticketbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;



@Document(collection = "airports")
public class Airport {

    @Id
    private String airportId;
    private String countryName;
    private String airportName;
    private String cityServed;
    public Airport() {
    }

    public Airport(String countryName, String airportName, String cityServed) {
        this.countryName = countryName;
        this.airportName = airportName;
        this.cityServed = cityServed;
    }

    public Airport(String airportId, String countryName, String airportName, String cityServed) {
        this.airportId = airportId;
        this.countryName = countryName;
        this.airportName = airportName;
        this.cityServed = cityServed;
    }

    public String getAirportId() {
        return airportId;
    }

    public void setAirportId(String airportId) {
        this.airportId = airportId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityServed() {
        return cityServed;
    }

    public void setCityServed(String cityServed) {
        this.cityServed = cityServed;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
}
