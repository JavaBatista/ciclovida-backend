package com.javabatista.biking.controller;

import com.javabatista.biking.dtos.UserRequest;
import com.javabatista.biking.model.CyclingDay;
import com.javabatista.biking.model.CyclingMonth;
import com.javabatista.biking.model.User;
import com.javabatista.biking.repository.CyclingDayRepository;
import com.javabatista.biking.repository.CyclingMonthRepository;
import com.javabatista.biking.repository.UserRepository;
import com.javabatista.biking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repository;
    @Autowired
    private CyclingDayRepository daysRepository;
    @Autowired
    private CyclingMonthRepository monthRepository;

    @GetMapping("day")
    public CyclingDay getDay(@RequestBody UserRequest userRequest){
        User user = repository.getReferenceById(userRequest.getUserId());

        return daysRepository.findByUserAndDate(user, userRequest.getDate());
    }

    @GetMapping("month/days")
    public List<CyclingDay> getDaysOfMonth(@RequestBody UserRequest userRequest){
        User user = repository.getReferenceById(userRequest.getUserId());

        return daysRepository.findByYearAndMonth(user,
                userRequest.getDate().getYear(),
                userRequest.getDate().getMonthValue());
    }

    @GetMapping("month/dates")
    public List<LocalDate> getDatesOfMonth(@RequestBody UserRequest userRequest){
        User user = repository.getReferenceById(userRequest.getUserId());

        return daysRepository.findDatesOfMonth(user,
                userRequest.getDate().getYear(),
                userRequest.getDate().getMonthValue());
    }

    @GetMapping("year/months/stats")
    public List<CyclingMonth> getCyclingMonths(@RequestBody UserRequest userRequest) {
        User user = repository.getReferenceById(userRequest.getUserId());
        return monthRepository.findByYear(user, userRequest.getDate().getYear());
    }

    @GetMapping("year/dates")
    public List<LocalDate> getDatesOfYear(@RequestBody UserRequest userRequest) {
        User user = repository.getReferenceById(userRequest.getUserId());
        return daysRepository.findDatesOfYear(user, userRequest.getDate().getYear());
    }
}
