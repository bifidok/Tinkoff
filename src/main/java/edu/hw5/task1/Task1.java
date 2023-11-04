package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task1 {
    private final static String DATETIME_PATTERN = "yyyy-MM-dd, HH:mm";
    private final static int SECONDS_IN_MINUTE = 60;

    private Task1() {
    }

    public static String averageTime(String[] dates) {
        if (dates == null || dates.length == 0) {
            return getTime(0).toString();
        }
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
        return LocalDateTime.parse(text, formatter);
    }

    private static Time getTime(long averageSeconds) {
        int seconds = (int) (averageSeconds % SECONDS_IN_MINUTE);
        int minutes = (int) (averageSeconds / SECONDS_IN_MINUTE % SECONDS_IN_MINUTE);
        int hours = (int) (averageSeconds / (SECONDS_IN_MINUTE * SECONDS_IN_MINUTE));
        return new Time(hours, minutes, seconds);
    }
}
