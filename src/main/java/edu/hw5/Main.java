package edu.hw5;

import edu.hw5.task1.Task1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.intellij.lang.annotations.Pattern;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        String [] dates = new String[]{"2022-03-12, 20:20 - 2022-03-12, 23:50",
        "2022-04-01, 21:30 - 2022-04-02, 01:20"};
        System.out.println(Task1.averageTime(dates));
    }
}
