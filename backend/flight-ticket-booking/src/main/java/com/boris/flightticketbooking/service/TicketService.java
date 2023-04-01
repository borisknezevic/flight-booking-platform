package com.boris.flightticketbooking.service;

import com.boris.flightticketbooking.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> buyTickets(List<Ticket> tickets, String flightId);
    List<Ticket> getBoughtTickets();
}
