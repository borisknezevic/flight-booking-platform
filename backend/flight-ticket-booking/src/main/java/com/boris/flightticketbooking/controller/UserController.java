package com.boris.flightticketbooking.controller;

import com.boris.flightticketbooking.entity.Ticket;
import com.boris.flightticketbooking.entity.User;
import com.boris.flightticketbooking.entity.dto.TicketDTO;
import com.boris.flightticketbooking.entity.dto.TicketsDTO;
import com.boris.flightticketbooking.entity.dto.UserDTO;
import com.boris.flightticketbooking.entity.dto.UserInfoDTO;
import com.boris.flightticketbooking.service.TicketService;
import com.boris.flightticketbooking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("http://localhost:5173")
public class UserController {

    private final UserService userService;
    private final TicketService ticketService;

    public UserController(UserService userService, TicketService ticketService) {
        this.userService = userService;
        this.ticketService = ticketService;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.ACCEPTED );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable String id){
        User user = userService.getUserInfo(id);
        UserInfoDTO userInfoDTO = new UserInfoDTO(user.getFirstName(),  user.getLastName());
        return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
    }


    //nije testirano
    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDTO>> getBoughtTickets(){
        System.out.println("GET_USER_TICKET");
        List<Ticket> tickets = ticketService.getBoughtTickets();
        List<TicketDTO> ticketDTOS = new ArrayList<>();
        for (Ticket t: tickets
             ) {
                TicketDTO ticketDTO = new TicketDTO(
                        t.getFlight().getId(),
                        t.getBuyer().getId(),
                        t.getPassengerFirstName(),
                        t.getPassengerLastName(),
                        t.getFlight().getDepartureDate(),
                        t.getFlight().getCurrentLocation().getCityServed(),
                        t.getFlight().getDestination().getCityServed(),
                        t.getFlight().getPrice()
                );
                ticketDTOS.add(ticketDTO);
        }
        return new ResponseEntity<>(ticketDTOS, HttpStatus.OK);
    }
}
