package edu.project3;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private static final Pattern DATE_TIME_PATTERN = Pattern
        .compile("(?<datetime>\\d{2}/\\w{3}/\\d{4}:\\d{2}:\\d{2}:\\d{2} \\+\\d+)");
    private static final Pattern RESOURCE_PATTERN = Pattern
        .compile("(?<resource>\"[A-Z]+ /.+\")");
    private static final Pattern RESPONSE_CODE_PATTERN = Pattern
        .compile("(?<responseCode>\\d{3})");
    private static final Pattern RESPONSE_SIZE_PATTERN = Pattern
        .compile("(?<responseSize>\\d+)");
    private static final Pattern USERAGENT_PATTERN = Pattern
        .compile("(?<useragent>\".*\"$)");
    private static final Pattern LOG_PATTERN = Pattern
        .compile(".*" + DATE_TIME_PATTERN
            + ".*" + RESOURCE_PATTERN
            + " " + RESPONSE_CODE_PATTERN + " "
            + RESPONSE_SIZE_PATTERN + ".*"
            + USERAGENT_PATTERN);

    private static final String DATE_TIME_FORMAT = "dd/MMM/yyyy:HH:mm:ss Z";

    public Log parse(String log) {
        Matcher matcher = LOG_PATTERN.matcher(log);
        if(!matcher.matches()) return null;
        OffsetDateTime dateTime = getDateTime(matcher);
        String resource = getResource(matcher);
        String respCode = getResponseCode(matcher);
        long respSize = getResponseSize(matcher);
        return new Log(dateTime, resource, respCode, respSize);
    }

    private OffsetDateTime getDateTime(Matcher matcher) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        OffsetDateTime dateTime = OffsetDateTime.parse(matcher.group("datetime"), formatter);
        return dateTime;
    }

    private String getResource(Matcher matcher) {
        return matcher.group("resource");
    }

    private String getResponseCode(Matcher matcher) {
        return matcher.group("responseCode");
    }

    private long getResponseSize(Matcher matcher) {
        String responseSizeStr = matcher.group("responseSize");
        return Long.parseLong(responseSizeStr);
    }
}
