package edu.hw5.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ZeroAndOneAlphabetTest {
    @Test
    @DisplayName("Не менее трех символов, третий равен 0(положительный)")
    public void atLeastThreeSymbols_shouldReturnTrue() {
        String input = "0101001";
        var result = ZeroAndOneAlphabet.atLeastThreeSymbols(input);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Не менее трех символов, третий равен 0(отрицательный)")
    public void atLeastThreeSymbols_shouldReturnFalse_whenThirdIsNotZero() {
        String input = "011001";
        var result = ZeroAndOneAlphabet.atLeastThreeSymbols(input);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Не менее трех символов, третий равен 0(отрицательный)")
    public void atLeastThreeSymbols_shouldReturnFalse_whenLengthLessThanThree() {
        String input = "1";
        var result = ZeroAndOneAlphabet.atLeastThreeSymbols(input);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Не менее трех символов, невалидная строка")
    public void atLeastThreeSymbols_shouldReturnFalse_whenIncorrectString() {
        String input = "9";
        var result = ZeroAndOneAlphabet.atLeastThreeSymbols(input);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Один и тот же символ в начале и конце(положительный)")
    public void sameSymbolAtStartAndEnd_shouldReturnTrue() {
        String input = "11";
        var result = ZeroAndOneAlphabet.sameSymbolAtStartAndEnd(input);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Один и тот же символ в начале и конце(отрицательный)")
    public void sameSymbolAtStartAndEnd_shouldReturnFalse() {
        String input = "000001011";
        var result = ZeroAndOneAlphabet.sameSymbolAtStartAndEnd(input);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Один и тот же символ в начале и конце, невалидная строка")
    public void sameSymbolAtStartAndEnd_shouldReturnFalse_whnIncorrectString() {
        String input = "8765898";
        var result = ZeroAndOneAlphabet.sameSymbolAtStartAndEnd(input);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Длина не менее 1 и не более 3(положительный)")
    public void lengthFromOneToThree_shouldReturnTrue() {
        String input = "010";
        var result = ZeroAndOneAlphabet.lengthFromOneToThree(input);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Длина не менее 1 и не более 3(отрицательный)")
    public void lengthFromOneToThree_shouldReturnFalse() {
        String input = "";
        var result = ZeroAndOneAlphabet.lengthFromOneToThree(input);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Длина не менее 1 и не более 3, невалидная строка")
    public void lengthFromOneToThree_shouldReturnFalse_whenIncorrectString() {
        String input = "362";
        var result = ZeroAndOneAlphabet.lengthFromOneToThree(input);

        assertThat(result).isFalse();
    }
}
