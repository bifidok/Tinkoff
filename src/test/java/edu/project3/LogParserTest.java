package edu.project3;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LogParserTest {
    @Test
    @DisplayName("Валидный лог")
    public void parse_shouldParseLog(){
        String logString = "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" " +
            "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        String resource = "\"GET /downloads/product_1 HTTP/1.1\"";
        String useragent = "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        String responseCode = "304";
        int responseSize = 0;
        OffsetDateTime dateTime = OffsetDateTime.of(
            LocalDate.of(2015,5,17),
            LocalTime.of(8,5,32),
            ZoneOffset.ofHours(0)
        );

        Log log = LogParser.parse(logString);

        assertThat(log.dateTime()).isEqualTo(dateTime);
        assertThat(log.responseSize()).isEqualTo(responseSize);
        assertThat(log.resource()).isEqualTo(resource);
        assertThat(log.useragent()).isEqualTo(useragent);
        assertThat(log.responseCode()).isEqualTo(responseCode);
    }

    @Test
    @DisplayName("Невалидный лог")
    public void parse_shouldNotParseLog_whenInvalidLog(){
        String logString = "93.180.71.3 - - \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" " +
            "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";

        Log log = LogParser.parse(logString);

        assertThat(log).isNull();
    }
}
