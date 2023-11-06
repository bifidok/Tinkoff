package edu.project3;

import java.time.OffsetDateTime;

public record Log(OffsetDateTime dateTime, String resource, String responseCode, long responseSize) {
}
