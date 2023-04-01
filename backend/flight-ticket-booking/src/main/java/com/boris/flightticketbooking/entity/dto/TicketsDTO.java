package com.boris.flightticketbooking.entity.dto;

import com.boris.flightticketbooking.entity.Ticket;

import java.util.List;

public class TicketsDTO {

    private String flightId;
    private List<Ticket> tickets;

    public TicketsDTO(String flightId, List<Ticket> tickets) {
        this.flightId = flightId;
        this.tickets = tickets;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "TicketsDTO{" +
                "flightId='" + flightId + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
