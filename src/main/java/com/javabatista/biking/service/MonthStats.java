package com.javabatista.biking.service;

import com.javabatista.biking.model.CyclingDay;
import com.javabatista.biking.model.CyclingMonth;
import com.javabatista.biking.model.User;
import com.javabatista.biking.repository.CyclingMonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class MonthStats {

    @Autowired
    CyclingMonthRepository repository;

    public void updateMonth(List<CyclingDay> cyclingDays, User user) {

        CyclingMonth cyclingMonth;
        LocalDate date;

        Integer totalDays = 0;
        Double totalDistance  = 0.0;
        Duration totalTime;

        for (CyclingDay day: cyclingDays ) {
            date = LocalDate.of(day.getDate().getYear(), day.getDate().getMonth(), 1);

            cyclingMonth = repository.findByDate(date);
            if (cyclingMonth == null) {
                cyclingMonth =new CyclingMonth();
                cyclingMonth.setDate(date);
                cyclingMonth.setTotalDays(1);
                cyclingMonth.setTotalDistance(day.getDistance());
                cyclingMonth.setTotalTime(day.getDuration());
            } else {
                totalDistance = day.getDistance() + cyclingMonth.getTotalDistance();
                totalTime = cyclingMonth.getTotalTime().plus(day.getDuration());
                totalDays = cyclingMonth.getTotalDays() + 1;

                cyclingMonth.setTotalDays(totalDays);
                cyclingMonth.setTotalDistance(totalDistance);
                cyclingMonth.setTotalTime(totalTime);

            }
            cyclingMonth.setUser(user);
            repository.save(cyclingMonth);

        }

    }

    public void updateMonth(CyclingDay day, User user) {

        List<CyclingDay> cyclingDay = Arrays.asList(day);

        updateMonth(cyclingDay, user);

    }
}
