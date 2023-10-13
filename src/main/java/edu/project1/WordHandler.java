package edu.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WordHandler implements Dictionary {
    private final static String DEFAULT_WORD = "tinkoff";
    private final static int WORDS_IN_DICTIONARY_COUNT = 5;
    private String word;
    private char[] maskedWord;
    private Map<Integer, Character> positionToLetter;

    public void generateNewWord() {
        word = getRandomWord();
        maskedWord = new char[word.length()];
        positionToLetter = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            positionToLetter.put(i, word.charAt(i));
        }
        setMaskedWord();
    }

    public boolean containsLetter(char letter) {
        if (positionToLetter.containsValue(letter)) {
            updateMaskedWord(letter);
            return true;
        }
        return false;
    }

    public boolean isAllLettersOpened() {
        return positionToLetter.size() == 0;
    }

    public String getMaskedWord() {
        return Arrays.toString(maskedWord);
    }

    private void updateMaskedWord(char letter) {
        for (int i = 0; i < maskedWord.length; i++) {
            if (word.charAt(i) == letter) {
                maskedWord[i] = letter;
                positionToLetter.remove(i);
            }
        }
    }

    private String setMaskedWord() {
        Arrays.fill(maskedWord, '*');
        return Arrays.toString(maskedWord);
    }

    private String getRandomWord() {
        word = DEFAULT_WORD;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/edu/project1/Dictionary.txt"))) {
            Random random = new Random();
            int randomLine = random.nextInt(WORDS_IN_DICTIONARY_COUNT);
            for (int i = 0; i < randomLine; i++) {
                reader.readLine();
            }
            word = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return word;
    }
}
