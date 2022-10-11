package com.javabatista.biking.model;

import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "cycling_day")
public class CyclingDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Integer userId;
    private LocalDate date;
    private Instant startTime;
    private Instant finishTime;
    private LocalTime cyclingTime;
    private Double distance;
    private Double odometer;
    private Double maxSpeed;
    private Double avgSpeed;
    private WindCondition windCondition;
    private String comments;
    private Duration duration;
    private Double cyclingQuality;

    public CyclingDay() {
    }

    public CyclingDay(LocalDate date,
                      Instant startTime,
                      Instant finishTime,
                      LocalTime cyclingTime,
                      Double distance,
                      Double odometer,
                      Double maxSpeed,
                      Double avgSpeed,
                      WindCondition windCondition, String comments) {

        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.cyclingTime = cyclingTime;
        this.distance = distance;
        this.odometer = odometer;
        this.maxSpeed = maxSpeed;
        this.avgSpeed = avgSpeed;
        this.windCondition = windCondition;
        this.comments = comments;
        this.duration = Duration.between(startTime, finishTime);
        this.cyclingQuality = distance * avgSpeed;
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

    public Duration getDuration() {
        return Duration.between(startTime, finishTime);
    }

    public Double getCyclingQuality() {
        return distance * avgSpeed;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Instant finishTime) {
        this.finishTime = finishTime;
    }

    public LocalTime getCyclingTime() {
        return cyclingTime;
    }

    public void setCyclingTime(LocalTime cyclingTime) {
        this.cyclingTime = cyclingTime;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getOdometer() {
        return odometer;
    }

    public void setOdometer(Double odometer) {
        this.odometer = odometer;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public WindCondition getWindCondition() {
        return windCondition;
    }

    public void setWindCondition(WindCondition windCondition) {
        this.windCondition = windCondition;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "CyclingDay{" +
                "date=" + date +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", cyclingTime=" + cyclingTime +
                ", distance=" + distance +
                ", odometer=" + odometer +
                ", maxSpeed=" + maxSpeed +
                ", avgSpeed=" + avgSpeed +
                ", windCondition=" + windCondition +
                ", comments='" + comments + '\'' +
                ", duration=" + duration +
                ", cyclingQuality=" + cyclingQuality +
                '}';
    }
}
