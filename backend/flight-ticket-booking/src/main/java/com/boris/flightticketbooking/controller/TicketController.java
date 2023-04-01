package com.boris.flightticketbooking.controller;

import com.boris.flightticketbooking.entity.Ticket;
import com.boris.flightticketbooking.entity.dto.TicketsDTO;
import com.boris.flightticketbooking.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@CrossOrigin("http://localhost:5173")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Integer> buyTickets(@RequestBody TicketsDTO tickets){
        System.out.println(tickets);
        List<Ticket> t =  ticketService.buyTickets(tickets.getTickets(), tickets.getFlightId());
        return new ResponseEntity<>(t.size(), HttpStatus.CREATED);
    }

}
