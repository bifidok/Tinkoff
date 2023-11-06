package edu.project3;

import java.util.Map;

public record Statistic(
    int logsCount,
    Map<String, Integer> resourceToRequestCount,
    Map<String, Integer> responseCodeToCount,
    long averageServerResponseSize) {
}
