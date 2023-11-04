package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

public class Parser6 extends MiddleWare {
    private final static Pattern DATETIME_PATTERN = Pattern.compile("(\\d+) days? ago");

    @Override
    public Optional<LocalDate> parseDate(String date) {
        var matcher = DATETIME_PATTERN.matcher(date);
        if (matcher.matches()) {
            int daysAgo = Integer.parseInt(matcher.group(1));
            LocalDate localDate = LocalDate.now().minusDays(daysAgo);
            return Optional.of(localDate);
        }
        return checkNext(date);
    }
}
