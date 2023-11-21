package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser {
    private final static MiddleWare MIDDLE_WARE = MiddleWare.link(
        new Parser1(),
        new Parser2(),
        new Parser3(),
        new Parser4(),
        new Parser5(),
        new Parser6()
    );

    private DateParser() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        return MIDDLE_WARE.parseDate(string);
    }
}
