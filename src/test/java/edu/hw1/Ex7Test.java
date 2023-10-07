package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Ex7Test {
    @Test
    @DisplayName("Сдвиг влево на 3 бита")
    public void rotateLeft_shouldRotateThreeBites_basicTest() {
        int input = 9999;
        int ans = 14460;

        int output = Exercises.rotateLeft(input, 3);

        assertThat(output).isEqualTo(ans);
    }

    @Test
    @DisplayName("1 бит в числе(влево)")
    public void rotateLeft_shouldRotateSameAsInput_SingleBit() {
        int input = 1;
        int ans = 1;

        int output = Exercises.rotateLeft(input, 9);

        assertThat(output).isEqualTo(ans);
    }

    @Test
    @DisplayName("Все 32 бита(влево)")
    public void rotateLeft_shouldRotateSameAsInput_whenShiftOverBitCount() {
        int input = Integer.MAX_VALUE - 1;
        int ans = 2147483631;

        int output = Exercises.rotateLeft(input, 4);

        assertThat(output).isEqualTo(ans);
    }

    @Test
    @DisplayName("Сдвиг вправо на 3 бита")
    public void rotateRight_shouldRotateThreeBites_basicTest() {
        int input = 9999;
        int ans = 15585;

        int output = Exercises.rotateRight(input, 3);

        assertThat(output).isEqualTo(ans);
    }

    @Test
    @DisplayName("1 бит в числе(вправо)")
    public void rotateRight_shouldRotateSameAsInput_SingleBit() {
        int input = 1;
        int ans = 1;

        int output = Exercises.rotateRight(input, 9);

        assertThat(output).isEqualTo(ans);
    }

    @Test
    @DisplayName("Все 32 бита(вправо)")
    public void rotateRight_shouldRotateSameAsInput_whenShiftOverBitCount() {
        int input = Integer.MAX_VALUE - 1;
        int ans = 2013265919;

        int output = Exercises.rotateRight(input, 4);

        assertThat(output).isEqualTo(ans);
    }
}
