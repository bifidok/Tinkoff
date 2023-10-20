package edu.hw3.task3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FrequencyTest {
    @Test
    @DisplayName("Базовый тест со строками")
    public void freqDict_shouldReturnCorrectMapOfString() {
        var input = List.of("this", "and", "that", "and");
        var answer = Map.of(
            "this", 1,
            "that", 1,
            "and", 2
        );

        var result = Frequency.freqDict(input);

        for (Map.Entry<String, Integer> entry : answer.entrySet()) {
            assertThat(result.containsKey(entry.getKey())).isTrue();
            assertThat(result.get(entry.getKey()) == entry.getValue());
        }
    }

    @Test
    @DisplayName("Базовый тест с числами")
    public void freqDict_shouldReturnCorrectMapOfInt() {
        var input = List.of(1, 1, 1, 2, 5, 2);
        var answer = Map.of(
            1, 3,
            2, 2,
            5, 1
        );

        var result = Frequency.freqDict(input);

        for (Map.Entry<Integer, Integer> entry : answer.entrySet()) {
            assertThat(result.containsKey(entry.getKey())).isTrue();
            assertThat(result.get(entry.getKey()) == entry.getValue());
        }
    }

    @Test
    @DisplayName("На вход пустой лист")
    public void freqDict_shouldReturnEmptyMap() {
        var input = List.of();

        var result = Frequency.freqDict(input);

        assertThat(result.isEmpty()).isTrue();
    }

}
