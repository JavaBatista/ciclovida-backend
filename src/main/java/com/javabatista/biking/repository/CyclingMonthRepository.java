package com.javabatista.biking.repository;

import com.javabatista.biking.model.CyclingMonth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CyclingMonthRepository extends JpaRepository<CyclingMonth, Integer> {
}
