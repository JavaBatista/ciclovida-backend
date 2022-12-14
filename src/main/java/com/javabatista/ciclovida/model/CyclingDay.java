package com.javabatista.ciclovida.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "cycling_day")
public class CyclingDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnore
    private User user;
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
    @JsonIgnore
    private Duration duration;
    @Transient
    private String totalTime = getTotalTime();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getTotalTime() {
        if (duration != null) {
            return String.format("%02d:%02d:%02d", (int) (duration.toHours() % 24),
                    (int) (duration.toMinutes() % 60),
                    (int) (duration.getSeconds() % 60));
        }
        return totalTime;
    }

    private void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CyclingDay)) return false;
        CyclingDay that = (CyclingDay) o;
        return user.equals(that.user) && date.equals(that.date) && duration.equals(that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, date, duration);
    }
}
