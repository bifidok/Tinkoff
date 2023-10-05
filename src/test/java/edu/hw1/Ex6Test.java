package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Ex6Test {
    @Test
    @DisplayName("Есть постоянная Капрекара")
    public void countK_shouldReturnFour_basicTest() {
        int input = 1001;

        int output = Exercises.countK(input);

        assertThat(output).isEqualTo(4);
    }

    @Test
    @DisplayName("На вход постоянная Капрекара")
    public void countK_shouldOne_whenAlreadyConstK() {
        int input = 6174;

        int output = Exercises.countK(input);

        assertThat(output).isEqualTo(1);
    }

    @Test
    @DisplayName("В числе меньше 4 цифр")
    public void countK_shouldMinusOne_whenInvalidInput() {
        int input = 1;

        int output = Exercises.countK(input);

        assertThat(output).isEqualTo(-1);
    }

    @Test
    @DisplayName("В числе одинаковые цифры")
    public void countK_shouldMinusOne_whenSameDigits() {
        int input = 9999;

        int output = Exercises.countK(input);

        assertThat(output).isEqualTo(-1);
    }
}
