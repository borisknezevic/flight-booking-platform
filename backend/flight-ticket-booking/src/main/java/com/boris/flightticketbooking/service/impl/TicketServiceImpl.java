package com.boris.flightticketbooking.service.impl;

import com.boris.flightticketbooking.entity.Flight;
import com.boris.flightticketbooking.entity.Ticket;
import com.boris.flightticketbooking.entity.User;
import com.boris.flightticketbooking.repository.FlightRepository;
import com.boris.flightticketbooking.repository.TicketRepository;
import com.boris.flightticketbooking.repository.UserRepository;
import com.boris.flightticketbooking.service.TicketService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, FlightRepository flightRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Ticket> buyTickets(List<Ticket> tickets, String flightId) {
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        Flight flight = flightRepository.findById(flightId).get();
        if((flight.getTicketNum() + tickets.size()) < 0  )
            throw new RuntimeException("Nema dovoljno slobodnih karata");

        //Get user id from spring security
        User user = userRepository.findByEmail(auth.getName()).get();
//        User user  = userRepository.findById("640d1bb14552d27213670ef2").get();


        for (Ticket ticket :
                tickets) {
            ticket.setFlight(flight);
            ticket.setBuyer(user);
            flight.buyTicket(ticket);
            ticketRepository.save(ticket);
        }

        //Tickets left
        flight.setTicketNum(flight.getTicketNum() - tickets.size());
        flightRepository.save(flight);

        return tickets;
    }

    @Override
    public List<Ticket> getBoughtTickets() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        System.out.println("Username: " + userDetails.getUsername());


        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        System.out.println(user.toString());
        List<Ticket> tickets = ticketRepository.findByBuyer(user);
        return tickets;
    }
}
