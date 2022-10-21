package com.javabatista.ciclovida.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "cycling_month")
public class CyclingMonth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Integer id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnore
    private User user;
    private LocalDate date;
    private Integer totalDays;
    private Double totalDistance;
    @JsonIgnore
    private Duration totalTime;
    @Transient
    private String totalDuration = getTotalDuration();

    public CyclingMonth() {
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

    public String getTotalDuration() {

        if (totalTime != null) {
            return String.format("%02d:%02d:%02d", (int) (totalTime.toHours() % 24),
                    (int) (totalTime.toMinutes() % 60),
                    (int) (totalTime.getSeconds() % 60));
        }
        return totalDuration;
    }

    private void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
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
                "date=" + date +
                "totalDays=" + totalDays +
                ", totalDistance=" + totalDistance + " Km"+
                ", totalTime=" + totalTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CyclingMonth)) return false;
        CyclingMonth that = (CyclingMonth) o;
        return id.equals(that.id) && user.equals(that.user) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, date);
    }
}
