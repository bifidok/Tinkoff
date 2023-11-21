package edu.hw5.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SubstringCheckerTest {
    @Test
    @DisplayName("Положительный результат")
    public void isSubstring_whenSubstring() {
        String string = "adjhajadkjabcdakjfkd";
        String substring = "abc";

        assertThat(SubstringChecker.isSubstring(substring, string)).isTrue();
    }

    @Test
    @DisplayName("Отрицательный результат")
    public void isSubstring_whenNotSubstring() {
        String string = "adjhajadkjabcdakjfkd";
        String substring = "atertbc";

        assertThat(SubstringChecker.isSubstring(substring, string)).isFalse();
    }

    @Test
    @DisplayName("Пустые строки")
    public void isSubstring_shouldReturnTrue_whenEmptyStrings() {
        String string = "";
        String substring = "";

        assertThat(SubstringChecker.isSubstring(substring, string)).isTrue();
    }

    @Test
    @DisplayName("Пустая подстрока")
    public void isSubstring_shouldReturnTrue_whenEmptySubstring() {
        String string = "asdfs";
        String substring = "";

        assertThat(SubstringChecker.isSubstring(substring, string)).isTrue();
    }
}
