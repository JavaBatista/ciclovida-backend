package com.javabatista.ciclovida.service;

import com.javabatista.ciclovida.model.CyclingDay;
import com.javabatista.ciclovida.model.CyclingMonth;
import com.javabatista.ciclovida.model.User;
import com.javabatista.ciclovida.repository.CyclingMonthRepository;
import com.javabatista.ciclovida.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CyclingMonthRepository monthRepository;
    @Autowired
    private PasswordEncoder encoder;

    public void createUser(User user){
        String pass = user.getPassword();
        //criptografando antes de salvar no banco
        user.setPassword(encoder.encode(pass));
        userRepository.save(user);
    }

    public void addDay(User user, CyclingDay cyclingDay) {
        user.setCyclingDay(cyclingDay);
        updateMonth(user, cyclingDay);
    }

    public void updateDay(User user, CyclingDay cyclingDay) {
        
    }

    private void updateMonth(User user, CyclingDay day) {

        LocalDate date = LocalDate.of(day.getDate().getYear(), day.getDate().getMonth(), 1);

        CyclingMonth cyclingMonth = monthRepository.findByUserAndDate(user,date);
        if (cyclingMonth == null) {
            cyclingMonth =new CyclingMonth();
            cyclingMonth.setDate(date);
            cyclingMonth.setTotalDays(1);
            cyclingMonth.setTotalDistance(day.getDistance());
            cyclingMonth.setTotalTime(day.getDuration());
        } else {
            double totalDistance = day.getDistance() + cyclingMonth.getTotalDistance();
            Duration totalTime = cyclingMonth.getTotalTime().plus(day.getDuration());
            int totalDays = cyclingMonth.getTotalDays() + 1;

            cyclingMonth.setTotalDays(totalDays);
            cyclingMonth.setTotalDistance(totalDistance);
            cyclingMonth.setTotalTime(totalTime);

        }
        cyclingMonth.setUser(user);
        monthRepository.save(cyclingMonth);

    }

    private void updateMonths(User user, List<CyclingDay> cyclingDays) {
        for (CyclingDay day: cyclingDays ) {
            updateMonth(user, day);
        }
    }

}
