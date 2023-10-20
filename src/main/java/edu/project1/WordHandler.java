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

    private final String[] words = new String[WORDS_IN_DICTIONARY_COUNT];
    private String word;
    private char[] maskedWord;
    private Map<Integer, Character> positionToLetter;

    public WordHandler() {
        saveAllWords();
    }

    public void generateNewWord() {
        word = getRandomWord();
        maskedWord = new char[word.length()];
        positionToLetter = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            positionToLetter.put(i, word.charAt(i));
        }
        setInitialMaskedWord();
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
        return new String(maskedWord);
    }

    private void updateMaskedWord(char letter) {
        for (int i = 0; i < maskedWord.length; i++) {
            if (word.charAt(i) == letter) {
                maskedWord[i] = letter;
                positionToLetter.remove(i);
            }
        }
    }

    private void setInitialMaskedWord() {
        Arrays.fill(maskedWord, '*');
    }

    private void saveAllWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/edu/project1/Dictionary.txt"))) {
            for (int i = 0; i < WORDS_IN_DICTIONARY_COUNT; i++) {
                words[i] = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRandomWord() {
        Random random = new Random();
        word = words[random.nextInt(0, words.length)];
        return word;
    }
}
