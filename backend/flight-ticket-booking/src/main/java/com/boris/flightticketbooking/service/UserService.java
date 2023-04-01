package com.boris.flightticketbooking.service;

import com.boris.flightticketbooking.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserInfo(String userId);
}
