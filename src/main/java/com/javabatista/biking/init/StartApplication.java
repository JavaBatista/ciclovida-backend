package com.javabatista.biking.init;

import com.javabatista.biking.model.CyclingDay;
import com.javabatista.biking.model.CyclingMonth;
import com.javabatista.biking.model.User;
import com.javabatista.biking.repository.CyclingDayRepository;
import com.javabatista.biking.repository.CyclingMonthRepository;
import com.javabatista.biking.repository.UserRepository;
import com.javabatista.biking.service.UserService;
import com.javabatista.biking.util.MockDbData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
public class StartApplication implements CommandLineRunner {
    @Autowired
    private UserRepository repository;
    @Autowired
    private CyclingDayRepository cyclingDayRepository;
    @Autowired
    private CyclingMonthRepository cyclingMonthRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MockDbData mockDbData;
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user = repository.findByUsername("admin");
        if(user==null){
            user = new User();
            user.setName("ADMIN");
            user.setUsername("admin");
            user.setPassword("admin123");
            user.setEmail("admin@admin.com");
            user.getRoles().add("MANAGERS");
            userService.createUser(user);
        }


        addUser("Gabriel Gomes");
        addUser("Maria Silva");
        addUser("Sergio Borges");

        inserData("Gabriel Gomes");
        inserData("Maria Silva");
        inserData("Sergio Borges");

        user = repository.findByUsername("gabriel");
        List<CyclingDay> days = null;
        List<CyclingMonth> months = null;
        if(user !=null) {
             days = cyclingDayRepository.findByYearAndMonth(user, 2022, 5);
             months = cyclingMonthRepository.findByYear(user,2022);
        }
        if(days !=null)
            for (CyclingDay day: days) {
                System.out.println(day);
            }
        if(months !=null)
            for (CyclingMonth month: months) {
                System.out.println(month);
            }
    }

    private void addUser(String name) {
        String firstName = name.split(" ")[0].toLowerCase();
        User user = repository.findByUsername(firstName);
        if(user ==null){
            user = new User();
            user.setName(name);
            user.setUsername(firstName);
            user.setPassword(String.format("%s123", firstName));
            user.setEmail(String.format("%s@user.com", firstName));
            user.getRoles().add("USERS");

            userService.createUser(user);
        }
    }

    private void inserData(String name) {
        String firstName = name.split(" ")[0].toLowerCase();
        User user = repository.findByUsername(firstName);
        if(user != null) {
            for (CyclingDay day : mockDbData.getCyclingDayList()) {
                userService.addDay(user, day);
            }

            repository.save(user);
        }
    }

}
