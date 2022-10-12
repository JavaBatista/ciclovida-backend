package com.javabatista.biking.init;

import com.javabatista.biking.model.CyclingDay;
import com.javabatista.biking.model.User;
import com.javabatista.biking.repository.CyclingDayRepository;
import com.javabatista.biking.repository.UserRepository;
import com.javabatista.biking.service.MonthStats;
import com.javabatista.biking.util.MockDbData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class StartApplication implements CommandLineRunner {
    @Autowired
    private UserRepository repository;
    @Autowired
    private CyclingDayRepository cyclingDayRepository;
    @Autowired
    private MonthStats stats;
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user = repository.findByUsername("admin");
        if(user==null){
            user = new User();
            user.setName("ADMIN");
            user.setUsername("admin");
            user.setPassword("admin1234");
            user.setEmail("admin@admin.com");
            user.getRoles().add("MANAGERS");
            repository.save(user);
        }
        user = repository.findByUsername("user");
        if(user ==null){
            user = new User();
            user.setName("USER");
            user.setUsername("user");
            user.setPassword("user1234");
            user.setEmail("user@user.com");
            user.getRoles().add("USERS");
//            CyclingDay cyclingDay = MockDbData.cyclingDayList.get(0);
//            user.setCyclingDay(cyclingDay);

            for (CyclingDay day: MockDbData.cyclingDayList) {
                user.setCyclingDay(day);
            }

            repository.save(user);
            stats.updateMonth(MockDbData.cyclingDayList, user);
        }

    }
}
