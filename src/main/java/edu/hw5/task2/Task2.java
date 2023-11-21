package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class Task2 {

    private Task2() {
    }

    public static List<LocalDate> getFridaysThirteen(int year) {
        if (year < 1) {
            throw new IllegalArgumentException("Invalid year");
        }
        LocalDate date = LocalDate.of(year, Month.JANUARY, 13);
        List<LocalDate> datesWithFridays = new ArrayList<>();
        for (int i = 0; i < Month.values().length; i++) {
            if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                datesWithFridays.add(date);
            }
            date = date.plusMonths(1);
        }
        return datesWithFridays;
    }

    public static LocalDate getNextFridayThirteen(LocalDate date) {
        LocalDate nextFridayThirteenDate = LocalDate.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        while (!(nextFridayThirteenDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)
            && nextFridayThirteenDate.getDayOfMonth() == 13)) {
            nextFridayThirteenDate = nextFridayThirteenDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return nextFridayThirteenDate;
    }
}
