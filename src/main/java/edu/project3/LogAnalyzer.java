package edu.project3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogAnalyzer {
    private int logsCount;
    private long summaryServerResponseSize;
    private Map<String, Integer> resourceToRequestCount;
    private Map<String, Integer> responseCodeToCount;

    public LogAnalyzer() {
        resourceToRequestCount = new HashMap<>();
        responseCodeToCount = new HashMap<>();
    }

    public Statistic analyze(File file, LocalDate from, LocalDate to) {
        LogParser parser = new LogParser();
        OffsetDateTime fromOffset;
        OffsetDateTime toOffset;
        if (from != null) {
            fromOffset = OffsetDateTime.of(LocalDateTime.of(from, LocalTime.NOON), ZoneOffset.UTC);
        }else{
            fromOffset = OffsetDateTime.of(LocalDateTime.of(LocalDate.EPOCH,LocalTime.NOON),ZoneOffset.UTC);
        }
        if (to != null) {
            toOffset = OffsetDateTime.of(LocalDateTime.of(to, LocalTime.NOON), ZoneOffset.UTC);
        }else{
            toOffset = OffsetDateTime.of(LocalDateTime.of(LocalDate.now(),LocalTime.NOON),ZoneOffset.UTC);
        }
        try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            stream
                .map(parser::parse)
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
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Statistic(
            logsCount,
            resourceToRequestCount,
            responseCodeToCount,
            summaryServerResponseSize / logsCount
        );
    }
}
