package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task1 {
    private final static String dateTimePattern = "yyyy-MM-dd, HH:mm";

    private Task1() {
    }

    public static String averageTime(String[] dates) {
        long seconds = 0L;

        for (String date : dates) {
            LocalDateTime start = parse(date.substring(0, date.length() / 2 - 1));
            LocalDateTime end = parse(date.substring(date.length() / 2 + 2));
            Duration duration = Duration.between(start, end);
            seconds += duration.toSeconds();
        }
        long averageSeconds = seconds / dates.length;
        return getTime(averageSeconds).toString();
    }

    private static LocalDateTime parse(String text) {
        Objects.requireNonNull(text);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        return LocalDateTime.parse(text, formatter);
    }

    private static Time getTime(long averageSeconds) {
        int seconds = (int) (averageSeconds % 60);
        averageSeconds -= seconds;
        int minutes = (int) (averageSeconds / 60 % 60);
        averageSeconds -= minutes * 60;
        int hours = (int) (averageSeconds / 3600);
        averageSeconds -= hours * 3600;
        return new Time(hours,minutes,seconds);
    }
}
