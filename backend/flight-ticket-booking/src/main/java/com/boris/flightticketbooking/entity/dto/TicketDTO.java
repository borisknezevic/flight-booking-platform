package com.boris.flightticketbooking.entity.dto;

import com.boris.flightticketbooking.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private String flightId;
    private String buyerId;
    private String passengerFirstName;
    private String passengerLastName;
    private LocalDate departureDate;
    private String departureCity;
    private String destinationCity;
    private float price;

}
