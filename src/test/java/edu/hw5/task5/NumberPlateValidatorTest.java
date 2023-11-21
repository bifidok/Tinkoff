package edu.hw5.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NumberPlateValidatorTest {
    @Test
    @DisplayName("Валидные номера")
    public void validate_shouldMatchAllNumbers() {
        String[] numbers = new String[] {
            "А123АА777",
            "В999ХУ23",
            "Х000ХХ00",
            "В999ХУ23",
            "К332МН88"
        };

        for (String number : numbers) {
            assertThat(NumberPlateValidator.validate(number)).isTrue();
        }
    }

    @Test
    @DisplayName("Невалидные номера")
    public void validate_shouldNotMatchAllNumbers() {
        String[] numbers = new String[] {
            "G123GG777",
            "999ХУ23",
            "Х00ХХ00",
            "В999ХУ3",
            " ",
            "",
            "1ААА33ААА",
            "К33МН88"
        };

        for (String number : numbers) {
            assertThat(NumberPlateValidator.validate(number)).isFalse();
        }
    }
}
