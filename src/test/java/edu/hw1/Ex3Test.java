package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class Ex3Test {
    @Test
    @DisplayName("Положительный результат")
    public void isNestable_shouldReturnTrue_whenNestable() {
        int[] a1 = new int[] {2, 3, 4, 5, 6};
        int[] a2 = new int[] {1, 9};

        assertThat(Exercises.isNestable(a1, a2)).isTrue();
    }

    @Test
    @DisplayName("Отрицательный результат")
    public void isNestable_shouldReturnFalse_whenNotNestable() {
        int[] a1 = new int[] {55, 33, 11, 9, 1, 543};
        int[] a2 = new int[] {44, 222, 0};

        assertThat(Exercises.isNestable(a1, a2)).isFalse();
    }

    @Test
    @DisplayName("Пустые массивы")
    public void isNestable_shouldReturnTrue_whenEmptyArray() {
        int[] a1 = new int[] {};
        int[] a2 = new int[] {44, 222, 0};

        boolean output = Exercises.isNestable(a1, a2);

        assertThat(output).isTrue();
    }

    @Test
    @DisplayName("Невалидные массивы")
    public void isNestable_shouldThrowException_whenArrayIsNull() {
        int[] a1 = null;
        int[] a2 = new int[] {44, 222, 0};

        Throwable thrown = catchThrowable(() -> {
            Exercises.isNestable(a1, a2);
        });

        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

}
