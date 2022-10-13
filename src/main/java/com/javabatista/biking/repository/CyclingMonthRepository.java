package com.javabatista.biking.repository;

import com.javabatista.biking.model.CyclingMonth;
import com.javabatista.biking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface CyclingMonthRepository extends JpaRepository<CyclingMonth, Integer> {

    CyclingMonth findByDateAndUser(LocalDate date, User user);
}
