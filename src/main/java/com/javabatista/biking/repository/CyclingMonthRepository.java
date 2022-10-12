package com.javabatista.biking.repository;

import com.javabatista.biking.model.CyclingMonth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CyclingMonthRepository extends JpaRepository<CyclingMonth, Integer> {
    CyclingMonth findByDate(LocalDate date);
}
