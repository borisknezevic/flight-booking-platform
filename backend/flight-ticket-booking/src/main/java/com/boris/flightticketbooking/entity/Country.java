package com.boris.flightticketbooking.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


import java.util.List;

@Document

public class Country {

    @Id
    private String countryId;
    private String countryName;

    private List<Airport> airport;

    public Country() {
    }

    public Country(String countryId, String countryName, List<Airport> airport) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.airport = airport;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public List<Airport> getAirport() {
        return airport;
    }

    public void setAirport(List<Airport> airport) {
        this.airport = airport;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
