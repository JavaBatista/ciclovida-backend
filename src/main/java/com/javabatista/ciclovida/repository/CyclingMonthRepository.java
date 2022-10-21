package com.javabatista.ciclovida.repository;

import com.javabatista.ciclovida.model.CyclingMonth;
import com.javabatista.ciclovida.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CyclingMonthRepository extends JpaRepository<CyclingMonth, Integer> {

    CyclingMonth findByUserAndDate(User user, LocalDate date);

    @Query("SELECT e FROM CyclingMonth e WHERE e.user= (:user) AND EXTRACT(YEAR FROM DATE)= (:year)")
    List<CyclingMonth> findByYear(@Param("user") User user, @Param("year") int year);
}
