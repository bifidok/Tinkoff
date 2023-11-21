package edu.hw5.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Среднее время сеанса")
    public void averageTime() {
        String[] dates = new String[] {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-03-12, 11:13 - 2022-03-12, 16:40",
            "2022-03-11, 20:41 - 2022-03-12, 02:50",
            "2022-03-12, 16:12 - 2022-03-12, 22:10",
        };

        String expected = "5ч 16м";
        String actual = Task1.averageTime(dates);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой массив")
    public void averageTime_shouldReturnZeroSeconds_whenEmptyArray() {
        String[] dates = new String[] {};

        String expected = "0с";
        String actual = Task1.averageTime(dates);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Null массив")
    public void averageTime_shouldReturnZeroSeconds_whenNullArray() {
        String[] dates = null;

        String expected = "0с";
        String actual = Task1.averageTime(dates);

        assertThat(actual).isEqualTo(expected);
    }
}
