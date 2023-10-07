package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Ex5Test {
    @Test
    @DisplayName("Есть палиндром")
    public void isPalindromeDescendant_shouldReturnTrue_whenPalindromeExist() {
        int input = 11211230;

        boolean output = Exercises.isPalindromeDescendant(input);

        assertThat(output).isTrue();
    }

    @Test
    @DisplayName("Нет палиндрома")
    public void isPalindromeDescendant_shouldReturnFalse_whenPalindromeDoesntExist() {
        int input = 11211231;

        boolean output = Exercises.isPalindromeDescendant(input);

        assertThat(output).isFalse();
    }

    @Test
    @DisplayName("Цифра на вход")
    public void isPalindromeDescendant_shouldReturnFalse_whenSingleDigit() {
        int input = 8;

        boolean output = Exercises.isPalindromeDescendant(input);

        assertThat(output).isTrue();
    }

    @Test
    @DisplayName("Нули на конце")
    public void isPalindromeDescendant_shouldReturnTrue_whenZeroesAtEnd() {
        int input = 23140000;

        boolean output = Exercises.isPalindromeDescendant(input);

        assertThat(output).isTrue();
    }

    @Test
    @DisplayName("Палиндром на вход")
    public void isPalindromeDescendant_shouldReturnTrue_whenAlreadyPalindrome() {
        int input = 44;

        boolean output = Exercises.isPalindromeDescendant(input);

        assertThat(output).isTrue();
    }
}
