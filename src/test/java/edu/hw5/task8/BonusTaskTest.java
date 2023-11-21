package edu.hw5.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BonusTaskTest {
    @Test
    @DisplayName("Нечетной длины(положительный)")
    public void oddLength_shouldReturnTrue() {
        String[] inputs = new String[] {
            "10100",
            "10010",
            "1010100",
            "0101010",
            "1",
        };
        for (String input : inputs) {
            assertThat(BonusTask.oddLength(input)).isTrue();
        }
    }

    @Test
    @DisplayName("Нечетной длины(отрицательный)")
    public void oddLength_shouldReturnFalse() {
        String[] inputs = new String[] {
            "1000",
            "1001101010",
            "101000",
            "010010",
            "11",
        };
        for (String input : inputs) {
            assertThat(BonusTask.oddLength(input)).isFalse();
        }
    }

    @Test
    @DisplayName("Начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину(положительный)")
    public void startsWithZeroAndHasOddLengthOrWithOneAndEvenLength_shouldReturnTrue() {
        String[] inputs = new String[] {
            "1000",
            "101111",
            "01101",
            "0100100",
            "0",
            "11",
        };

        for (String input : inputs) {
            assertThat(BonusTask.startsWithZeroAndHasOddLengthOrWithOneAndEvenLength(input)).isTrue();
        }
    }

    @Test
    @DisplayName("Начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину(отрицательный)")
    public void startsWithZeroAndHasOddLengthOrWithOneAndEvenLength_shouldReturnFalse() {
        String[] inputs = new String[] {
            "10100",
            "1011111",
            "011101",
            "01010100",
            "01",
            "111",
        };

        for (String input : inputs) {
            assertThat(BonusTask.startsWithZeroAndHasOddLengthOrWithOneAndEvenLength(input)).isFalse();
        }
    }

    @Test
    @DisplayName("Количество 0 кратно 3(положительный)")
    public void zeroCountIsMultipleOfThree_shouldReturnTrue() {
        String[] inputs = new String[] {
            "000",
            "101010",
            "0101010000100",
        };

        for (String input : inputs) {
            assertThat(BonusTask.zeroCountIsMultipleOfThree(input)).isTrue();
        }
    }

    @Test
    @DisplayName("Количество 0 кратно 3(отрицательный)")
    public void zeroCountIsMultipleOfThree_shouldReturnFalse() {
        String[] inputs = new String[] {
            "0000",
            "10101010",
            "01001010000100",
        };

        for (String input : inputs) {
            assertThat(BonusTask.zeroCountIsMultipleOfThree(input)).isFalse();
        }
    }

    @Test
    @DisplayName("Любая строка, кроме 11 или 111(положительный)")
    public void exceptTwoOrThreeOnes_shouldReturnTrue() {
        String[] inputs = new String[] {
            "0000",
            "10101010",
            "11000",
            "1111",
            "1"
        };

        for (String input : inputs) {
            assertThat(BonusTask.exceptTwoOrThreeOnes(input)).isTrue();
        }
    }

    @Test
    @DisplayName("Любая строка, кроме 11 или 111(отрицательный)")
    public void exceptTwoOrThreeOnes_shouldReturnFalse() {
        String[] inputs = new String[] {
            "11",
            "111",
        };

        for (String input : inputs) {
            assertThat(BonusTask.exceptTwoOrThreeOnes(input)).isFalse();
        }
    }

    @Test
    @DisplayName("Каждый нечетный символ равен 1(положительный)")
    public void everyOddIsOne_shouldReturnTrue() {
        String[] inputs = new String[] {
            "11111111",
            "1",
            "101010101",
        };

        for (String input : inputs) {
            assertThat(BonusTask.everyOddIsOne(input)).isTrue();
        }
    }

    @Test
    @DisplayName("Каждый нечетный символ равен 1(отрицательный)")
    public void everyOddIsOne_shouldReturnFalse() {
        String[] inputs = new String[] {
            "00000000",
            "010101010",
            "0",
        };

        for (String input : inputs) {
            assertThat(BonusTask.everyOddIsOne(input)).isFalse();
        }
    }

    @Test
    @DisplayName("Содержит не менее двух 0 и не более одной 1(положительный)")
    public void twoZeroesOneOne_shouldReturnTrue() {
        String[] inputs = new String[] {
            "000000100",
            "00000",
            "001",
            "100",
        };

        for (String input : inputs) {
            assertThat(BonusTask.twoZeroesOneOne(input)).isTrue();
        }
    }

    @Test
    @DisplayName("Содержит не менее двух 0 и не более одной 1(отрицательный)")
    public void twoZeroesOneOne_shouldReturnFalse() {
        String[] inputs = new String[] {
            "0001000100",
            "11",
            "1",
            "10",
        };

        for (String input : inputs) {
            assertThat(BonusTask.twoZeroesOneOne(input)).isFalse();
        }
    }

    @Test
    @DisplayName("Нет последовательных 1(положительный)")
    public void noConsistentOnes_shouldReturnTrue() {
        String[] inputs = new String[] {
            "0001000100",
            "101",
            "00000",
            "010",
        };

        for (String input : inputs) {
            assertThat(BonusTask.noConsistentOnes(input)).isTrue();
        }
    }

    @Test
    @DisplayName("Нет последовательных 1(отрицательный)")
    public void noConsistentOnes_shouldReturnFalse() {
        String[] inputs = new String[] {
            "11",
            "11111",
            "00110111",
            "111111",
        };

        for (String input : inputs) {
            assertThat(BonusTask.noConsistentOnes(input)).isFalse();
        }
    }
}
