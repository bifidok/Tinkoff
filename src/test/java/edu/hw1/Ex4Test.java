package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Ex4Test {
    @Test
    @DisplayName("Четная длина строки")
    public void fixString_shouldReturnString_evenStringLength() {
        String input = "оПомигети псаривьтс ртко!и";
        String ans = "Помогите исправить строки!";

        String output = Exercises.fixString(input);

        assertThat(output).isEqualTo(ans);
    }

    @Test
    @DisplayName("Нечетная длина строки")
    public void fixString_shouldReturnString_oddStringLength() {
        String input = "оПомигети псаривьтс рткои";
        String ans = "Помогите исправить строки";

        String output = Exercises.fixString(input);

        assertThat(output).isEqualTo(ans);
    }

    @Test
    @DisplayName("Длина строки - единица")
    public void fixString_shouldReturnString_whenSingleCharacter() {
        String input = "1";
        String ans = "1";

        String output = Exercises.fixString(input);

        assertThat(output).isEqualTo(ans);
    }

    @Test
    @DisplayName("Пустая строка")
    public void fixString_shouldReturnEmptyString_whenEmptyString() {
        String input = "";
        String ans = "";

        String output = Exercises.fixString(input);

        assertThat(output).isEqualTo(ans);
    }
}
