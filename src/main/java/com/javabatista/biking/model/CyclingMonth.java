package com.javabatista.biking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;

@Entity
@Table(name = "cycling_month")
public class CyclingMonth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false) //
    private User user;
    private LocalDate date;
    private Integer totalDays;
    private Double totalDistance;
    private Duration totalTime;

    public CyclingMonth() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Duration getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Duration totalTime) {
        this.totalTime = totalTime;
    }

    public CyclingMonth(LocalDate date, Integer totalDays, Double totalDistance, Duration totalTime) {
        this.date = date;
        this.totalDays = totalDays;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return "CyclingStats{" +
                "totalDays=" + totalDays +
                ", totalDistance=" + totalDistance + " Km"+
                ", totalTime=" + totalTime +
                '}';
    }
}
