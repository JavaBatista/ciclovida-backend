package com.javabatista.biking.controller;

import com.javabatista.biking.model.User;
import com.javabatista.biking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService service;

    @PostMapping("/create_user")
    @CrossOrigin
    public void postUser(@RequestBody User user){
        service.createUser(user);
    }
}
