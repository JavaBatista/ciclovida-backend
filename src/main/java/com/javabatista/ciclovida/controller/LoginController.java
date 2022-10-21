package com.javabatista.ciclovida.controller;

import com.javabatista.ciclovida.dtos.UserRequest;
import com.javabatista.ciclovida.model.User;
import com.javabatista.ciclovida.repository.UserRepository;
import com.javabatista.ciclovida.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Base64;

@RestController
public class LoginController {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repository;

    @CrossOrigin
    @GetMapping("/login")
    public ResponseEntity<UserRequest> logar(@RequestHeader(HttpHeaders.AUTHORIZATION) String header) {
        String[] parts = header.split(" ", 2);
        byte[] decodedBytes = Base64.getDecoder().decode(parts[1]);
        String authorization = new String(decodedBytes);

        parts = authorization.split(":", 2);
        String username = parts[0];

        UserRequest response = new UserRequest();
        User user = repository.findByEmail(username);
        if(user!=null) {
            response.setUserId(user.getId());
            response.setName(user.getName());
            response.setDate(LocalDate.now());
            return  new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

    }
}
