package edu.project3;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record Statistic(
    String fileName,
    int logsCount,
    List<Map.Entry<String, Integer>> resourceToRequestCount,
    List<Map.Entry<String, Integer>> responseCodeToCount,
    List<Map.Entry<String, Integer>> useragentToCount,
    List<Map.Entry<LocalDate, Integer>> dateToCount,
    long averageServerResponseSize) {
}
