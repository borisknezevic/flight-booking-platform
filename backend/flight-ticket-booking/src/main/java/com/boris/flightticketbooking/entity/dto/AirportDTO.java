package com.boris.flightticketbooking.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportDTO {

    private String countryName;
    private String airportName;
    private String cityServed;
}
