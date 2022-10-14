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

    @PostMapping("/login")
    public UserRequest logar(@RequestBody Login login) {
        UserRequest respnse = new UserRequest();
        User user = repository.findByUsername(login.getUsername());

        if(user!=null) {
            respnse.setUserId(user.getId());
            respnse.setDate(LocalDate.now());
        }

    return  respnse;
    }
}
