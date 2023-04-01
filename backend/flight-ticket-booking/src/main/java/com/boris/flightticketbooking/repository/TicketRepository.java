package com.boris.flightticketbooking.repository;

import com.boris.flightticketbooking.entity.Ticket;
import com.boris.flightticketbooking.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
    List<Ticket> findByBuyer(User user);
}
