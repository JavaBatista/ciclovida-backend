package com.javabatista.biking.controller;

import com.javabatista.biking.model.User;
import com.javabatista.biking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService service;
    @PostMapping("/create_user")
    public void postUser(@RequestBody User user){
        service.createUser(user);
    }
}
