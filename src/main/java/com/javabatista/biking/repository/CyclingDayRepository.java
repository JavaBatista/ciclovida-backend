package com.javabatista.biking.repository;

import com.javabatista.biking.model.CyclingDay;
import com.javabatista.biking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CyclingDayRepository extends JpaRepository<CyclingDay, Integer> {
    CyclingDay findByDateAndUser(LocalDate date, User user);
}
