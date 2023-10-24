package edu.project1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordHandler {
    private final Dictionary fileDictionary;

    private String word;
    private char[] maskedWord;
    private Map<Integer, Character> positionToLetter;

    public WordHandler() {
        fileDictionary = new FileDictionary();
        positionToLetter = new HashMap<>();
    }

    public void generateNewWord() {
        word = fileDictionary.generateNewWord();
        maskedWord = new char[word.length()];
        positionToLetter.clear();
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
}
