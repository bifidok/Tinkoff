package edu.hw5.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DateParserTest {
    @Test
    @DisplayName("Парсинг всех форматов")
    public void parseDate_shouldParseAllFormats() {
        String[] dates = new String[] {
            "2020-10-10",
            "2020-12-2",
            "1/3/1976",
            "1/3/20",
            "tomorrow",
            "today",
            "yesterday",
            "1 day ago",
            "2234 days ago",
        };

        for (String date : dates) {
            assertThat(DateParser.parseDate(date)).isNotEmpty();
        }
    }

    @Test
    @DisplayName("Невалидный формат")
    public void parseDate_shouldReturnEmpty() {
        String date = "1999/1/1";

        assertThat(DateParser.parseDate(date)).isEmpty();
    }
}
