package edu.hw5.task3;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class Parser5 extends MiddleWare {
    private final static String TOMORROW = "tomorrow";
    private final static String YESTERDAY = "yesterday";
    private final static String TODAY = "today";

    @Override
    @SuppressWarnings("InnerAssignment")
    public Optional<LocalDate> parseDate(String date) {
        Optional<LocalDate> parsedDate;
        switch (date) {
            case TOMORROW -> parsedDate = Optional.of(LocalDate.now().plusDays(1));
            case YESTERDAY -> parsedDate = Optional.of(LocalDate.now().with(t -> t.minus(Period.ofDays(1))));
            case TODAY -> parsedDate = Optional.of(LocalDate.now());
            default -> parsedDate = checkNext(date);
        }
        return parsedDate;
    }
}
