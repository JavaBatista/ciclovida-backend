package com.javabatista.biking.repository;

import com.javabatista.biking.model.CyclingDay;
import com.javabatista.biking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CyclingDayRepository extends JpaRepository<CyclingDay, Integer> {
    CyclingDay findByUserAndDate(User user, LocalDate date);

    List<CyclingDay> findAllByUser(User user);

    @Query("SELECT e FROM CyclingDay e WHERE e.user= (:user) AND EXTRACT(YEAR FROM DATE)= (:year) AND EXTRACT(MONTH FROM DATE)= (:month)")
    List<CyclingDay> findByYearAndMonth(@Param("user") User user, @Param("year") int year, @Param("month") int month);

    @Query("SELECT e.date FROM CyclingDay e WHERE e.user= (:user) AND EXTRACT(YEAR FROM DATE)= (:year) AND EXTRACT(MONTH FROM DATE)= (:month)")
    List<LocalDate> findDatesOfMonth(@Param("user") User user, @Param("year") int year, @Param("month") int month);
}
