package com.boris.flightticketbooking.service.impl;

import com.boris.flightticketbooking.entity.User;
import com.boris.flightticketbooking.repository.UserRepository;
import com.boris.flightticketbooking.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserInfo(String userId) {
        User user = userRepository.findById(userId).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal().toString() == user.getEmail()){
            System.out.println("Principal: " + authentication.getPrincipal().toString());
            System.out.println("User email: " + user.getEmail());
        }
        return user;
    }
}
