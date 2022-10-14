package com.javabatista.biking.controller;

import com.javabatista.biking.dtos.Login;
import com.javabatista.biking.dtos.UserRequest;
import com.javabatista.biking.model.User;
import com.javabatista.biking.repository.UserRepository;
import com.javabatista.biking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class LoginController {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repository;

    @GetMapping("/login")
    public UserRequest logar(@RequestParam(value = "username") String username) {
        UserRequest response = new UserRequest();
        User user = repository.findByUsername(username);

        if(user!=null) {
            response.setUserId(user.getId());
            response.setDate(LocalDate.now());
        }

    return  response;
    }
}
