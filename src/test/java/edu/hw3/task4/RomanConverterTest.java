package edu.hw3.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class RomanConverterTest {
    @Test
    @DisplayName("Базовый тест")
    public void converterToRoman_ShouldCorrectlyCovertToRoman() {
        int num = 112;
        String answer = "CXII";

        assertThat(RomanConverter.convertToRoman(num)).isEqualTo(answer);
    }

    @Test
    @DisplayName("Ноль на вход")
    public void converterToRoman_ShouldReturnNihil() {
        int num = 0;
        String answer = "Nihil";

        assertThat(RomanConverter.convertToRoman(num)).isEqualTo(answer);
    }

    @Test
    @DisplayName("Отрицательное число")
    public void converterToRoman_ShouldThrowException() {
        int num = -22;

        Throwable throwable = catchThrowable(() -> {
            RomanConverter.convertToRoman(num);
        });

        assertThat(throwable).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Roman number system does not have negative numbers");
    }
}
