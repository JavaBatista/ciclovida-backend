package com.javabatista.biking.util;

import java.time.Duration;
import java.time.LocalTime;

public class Util {
    public static LocalTime durationToLocalTime(Duration duration) {
        int hours = (int) (duration.toHours() % 24);
        int minutes = (int) (duration.toMinutes() % 60);
        int seconds = (int) (duration.getSeconds() % 60);
        return LocalTime.of(hours, minutes, seconds);
    }
}
