package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Ex2Test {
    @Test
    @DisplayName("Валидные входные данные")
    public void countDigits_shouldReturnCount_basicTest() {
        final int ITERATIONS = 1000;
        for (int i = 0; i < ITERATIONS; i++) {
            int offset = 120;
            int input = i * offset;
            int count = String.valueOf(input).length();

            int output = Exercises.countDigits(input);

            assertThat(output).isEqualTo(count);
        }
    }

    @Test
    @DisplayName("Отрицательные числа")
    public void countDigits_shouldReturnCount_whenNegativeNumbers() {
        final int ITERATIONS = 1000;
        for (int i = 1; i < ITERATIONS; i++) {
            int offset = -120;
            int input = i * offset;
            int count = String.valueOf(input).length() - 1;

            int output = Exercises.countDigits(input);

            assertThat(output).isEqualTo(count);
        }
    }
}
