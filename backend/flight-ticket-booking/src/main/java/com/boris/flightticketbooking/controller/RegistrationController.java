package com.boris.flightticketbooking.controller;

import com.boris.flightticketbooking.entity.User;
import com.boris.flightticketbooking.entity.dto.UserDTO;
import com.boris.flightticketbooking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/registration")
@CrossOrigin("http://localhost:5173")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO){
        User u = new User(userDTO);
        User newUser = userService.createUser(u);
        return new ResponseEntity<>(newUser.getId(), HttpStatus.CREATED);
    }
}
