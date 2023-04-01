package com.boris.flightticketbooking.entity.dto;

public class CityDTO {


    private String cityName;

    public CityDTO() {
    }

    public CityDTO(String cityName) {

        this.cityName = cityName;
    }



    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
