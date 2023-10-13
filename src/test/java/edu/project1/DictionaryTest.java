package edu.project1;

import edu.hw1.EvenArrayUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DictionaryTest {
    @Test
    @DisplayName("Проверка скрытого слова")
    void getMaskedWord_shouldReturnCorrectMaskedWord() {
        WordHandler dictionary = new WordHandler();
        dictionary.generateNewWord();
        String maskedWord = dictionary.getMaskedWord();
        assertThat(maskedWord).containsPattern("\\*").hasSizeGreaterThan(5);
    }
}
