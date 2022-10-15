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

    public static String durationToString(Duration duration) {
        return String.format("%02d:%02d:%02d", (int) (duration.toHours() % 24),
                (int) (duration.toMinutes() % 60),
                (int) (duration.getSeconds() % 60) );
    }
}
