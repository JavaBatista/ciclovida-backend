package com.javabatista.biking.repository;

import com.javabatista.biking.model.CyclingDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CyclingDayRepository extends JpaRepository<CyclingDay, Integer> {
}
