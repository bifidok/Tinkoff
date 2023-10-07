package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class Ex8Test {
    @Test
    @DisplayName("Кони не могут нападать")
    public void knightBoardCapture_shouldReturnTrue_whenKnightsCantAttack() {
        int[][] input = new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        boolean output = Exercises.knightBoardCapture(input);

        assertThat(output).isTrue();
    }

    @Test
    @DisplayName("Кони могут нападать")
    public void knightBoardCapture_shouldReturnFalse_whenKnightsCanAttack() {
        int[][] input = new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        boolean output = Exercises.knightBoardCapture(input);

        assertThat(output).isFalse();
    }

    @Test
    @DisplayName("Неправильная длина подмассивов")
    public void knightBoardCapture_shouldThrowException_whenIncorrectArrayLength() {
        int[][] input = new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        Throwable thrown = catchThrowable(() -> {
            Exercises.knightBoardCapture(input);
        });

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input array has incorrect length or null");
    }

    @Test
    @DisplayName("Пустой массив")
    public void knightBoardCapture_shouldThrowException_whenEmptyArray() {
        int[][] input = new int[][] {};

        Throwable thrown = catchThrowable(() -> {
            Exercises.knightBoardCapture(input);
        });

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input array has incorrect length or null");
    }

    @Test
    @DisplayName("Несозданный массив")
    public void knightBoardCapture_shouldThrowException_whenArrayNull() {
        int[][] input = null;

        Throwable thrown = catchThrowable(() -> {
            Exercises.knightBoardCapture(input);
        });

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input array has incorrect length or null");
        ;
    }
}
