package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Ex5Test {
    @Test
    @DisplayName("Есть палиндром")
    public void isPalindromeDescendant_shouldReturnTrue_whenPalindromeExist() {
        long input = 11211230;

        boolean output = Exercises.isPalindromeDescendant(input);

        assertThat(output).isTrue();
    }

    @Test
    @DisplayName("Нет палиндрома")
    public void isPalindromeDescendant_shouldReturnFalse_whenPalindromeDoesntExist() {
        long input = 11211231;

        boolean output = Exercises.isPalindromeDescendant(input);

        assertThat(output).isFalse();
    }

    @Test
    @DisplayName("Цифра на вход")
    public void isPalindromeDescendant_shouldReturnFalse_whenSingleDigit() {
        long input = 8;

        boolean output = Exercises.isPalindromeDescendant(input);

        assertThat(output).isFalse();
    }

    @Test
    @DisplayName("Нули на конце")
    public void isPalindromeDescendant_shouldReturnTrue_whenZeroesAtEnd() {
        long input = 233360140000L;

        boolean output = Exercises.isPalindromeDescendant(input);

        assertThat(output).isTrue();
    }

    @Test
    @DisplayName("Палиндром на вход")
    public void isPalindromeDescendant_shouldReturnTrue_whenAlreadyPalindrome() {
        long input = 44;

        boolean output = Exercises.isPalindromeDescendant(input);

        assertThat(output).isTrue();
    }
}
