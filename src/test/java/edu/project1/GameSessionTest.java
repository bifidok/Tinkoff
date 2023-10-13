package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameSessionTest {
    @Test
    @DisplayName("Корректное стартовое сообщение")
    void getMaskedWord_shouldReturnCorrectMaskedWord() {
        WordHandler dictionary = new WordHandler();
        dictionary.generateNewWord();
        String maskedWord = dictionary.getMaskedWord();
        assertThat(maskedWord).containsPattern("\\*").hasSizeGreaterThan(5);
    }

}
