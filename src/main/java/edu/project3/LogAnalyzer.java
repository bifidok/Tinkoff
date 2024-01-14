package edu.project3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogAnalyzer {
    private final static int TABLE_RAWS = 3;

    private int logsCount;
    private long summaryServerResponseSize;
    private final Map<String, Integer> resourceToRequestCount;
    private final Map<String, Integer> responseCodeToCount;
    private final Map<String, Integer> useragentToCount;
    private final Map<LocalDate, Integer> dateToCount;

    public LogAnalyzer() {
        resourceToRequestCount = new HashMap<>();
        responseCodeToCount = new HashMap<>();
        useragentToCount = new HashMap<>();
        dateToCount = new HashMap<>();
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public Statistic analyze(File file, LocalDate from, LocalDate to) {
        OffsetDateTime fromOffset = convertToOffsetDate(from, LocalDate.EPOCH);
        OffsetDateTime toOffset = convertToOffsetDate(to, LocalDate.now());
        try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            stream
                .map(LogParser::parse)
                .filter(log -> log.dateTime().isAfter(fromOffset) && log.dateTime().isBefore(toOffset))
                .forEach(log -> {
                    logsCount++;
                    summaryServerResponseSize += log.responseSize();
                    resourceToRequestCount.put(
                        log.resource(),
                        resourceToRequestCount.getOrDefault(log.resource(), 0) + 1
                    );
                    responseCodeToCount.put(
                        log.responseCode(),
                        responseCodeToCount.getOrDefault(log.responseCode(), 0) + 1
                    );
                    useragentToCount.put(
                        log.useragent(),
                        useragentToCount.getOrDefault(log.useragent(), 0) + 1
                    );
                    var logOffsetDateTime = log.dateTime();
                    var logDate = LocalDate.of(
                        logOffsetDateTime.getYear(),
                        logOffsetDateTime.getMonth(),
                        logOffsetDateTime.getDayOfMonth()
                    );
                    dateToCount.put(
                        logDate,
                        dateToCount.getOrDefault(logDate, 0) + 1
                    );
                });
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        if (logsCount == 0) {
            return null;
        }
        return new Statistic(
            file.getName(),
            logsCount,
            sortMap(resourceToRequestCount),
            sortMap(responseCodeToCount),
            sortMap(useragentToCount),
            sortMap(dateToCount),
            summaryServerResponseSize / logsCount
        );
    }

    private <T> List<Map.Entry<T, Integer>> sortMap(Map<T, Integer> map) {
        return map.entrySet().stream()
            .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
            .limit(TABLE_RAWS)
            .collect(Collectors.toList());
    }

    private OffsetDateTime convertToOffsetDate(LocalDate date, LocalDate defaultDate) {
        if (date != null) {
            return OffsetDateTime.of(LocalDateTime.of(date, LocalTime.NOON), ZoneOffset.UTC);
        }
        return OffsetDateTime.of(
            LocalDateTime.of(defaultDate, LocalTime.NOON),
            ZoneOffset.UTC
        );
    }
}
