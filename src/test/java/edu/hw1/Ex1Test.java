package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Ex1Test {

    @Test
    @DisplayName("Валидные входные данны)")
    public void minutesToSeconds_shouldReturnSeconds_basicTest() {
        final int ITERATIONS = 1000;
        for (int i = 0; i < ITERATIONS; i++) {
            long offset = 100;
            long seconds = i * offset;
            String input = String.format("%02d:%02d", seconds / 60, seconds % 60);

            long output = Exercises.minutesToSeconds(input);

            assertThat(output).isEqualTo(seconds);
        }
    }

    @Test
    @DisplayName("Количество секунд больше 60")
    public void minutesToSeconds_shouldReturnMinusOne_whenSecondsInvalid() {
        String input = "11:99";

        long output = Exercises.minutesToSeconds(input);

        assertThat(output).isEqualTo(-1);

    }

    @Test
    @DisplayName("Нули перед минутами и секундами")
    public void minutesToSeconds_shouldReturnSeconds_whenLeadingZeroes() {
        long seconds = 682;
        String input = "011:0022";

        long output = Exercises.minutesToSeconds(input);

        assertThat(output).isEqualTo(seconds);

    }

    @Test
    @DisplayName("Невалидная строка")
    public void minutesToSeconds_shouldReturnMinusOne_whenInvalidString() {
        String input = "fda11::99";

        long output = Exercises.minutesToSeconds(input);

        assertThat(output).isEqualTo(-1);

    }
}
