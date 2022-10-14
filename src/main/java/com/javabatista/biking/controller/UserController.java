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

    @GetMapping("/{userId}/day")
    public CyclingDay getDay(@PathVariable("userId") int userId, @RequestParam(value = "date") String date){
        User user = repository.getReferenceById(userId);

        return daysRepository.findByUserAndDate(user, LocalDate.parse(date));
    }

    @GetMapping("/{userId}/days/month")
    public List<CyclingDay> getDaysOfMonth(@PathVariable("userId") int userId, @RequestParam(value = "date") String date){
        User user = repository.getReferenceById(userId);
        LocalDate localDate = LocalDate.parse(date);
        return daysRepository.findByYearAndMonth(user,
                localDate.getYear(),
                localDate.getMonthValue());
    }

    @GetMapping("/{userId}/dates/month")
    public List<LocalDate> getDatesOfMonth(@PathVariable("userId") int userId, @RequestParam(value = "date") String date){
        User user = repository.getReferenceById(userId);
        LocalDate localDate = LocalDate.parse(date);
        return daysRepository.findDatesOfMonth(user,
                localDate.getYear(),
                localDate.getMonthValue());
    }

    @GetMapping("/{userId}/stats/months/year")
    public List<CyclingMonth> getCyclingMonths(@PathVariable("userId") int userId, @RequestParam(value = "date") String date) {
        User user = repository.getReferenceById(userId);
        LocalDate localDate = LocalDate.parse(date);
        return monthRepository.findByYear(user, localDate.getYear());
    }

    @GetMapping("/{userId}/dates/year")
    public List<LocalDate> getDatesOfYear(@PathVariable("userId") int userId, @RequestParam(value = "date") String date) {
        User user = repository.getReferenceById(userId);
        LocalDate localDate = LocalDate.parse(date);
        return daysRepository.findDatesOfYear(user, localDate.getYear());
    }
}
