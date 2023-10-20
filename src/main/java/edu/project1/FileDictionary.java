package edu.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class FileDictionary implements Dictionary {
    private final static int WORDS_IN_DICTIONARY_COUNT = 5;
    private final String[] words = new String[WORDS_IN_DICTIONARY_COUNT];

    public FileDictionary() {
        saveAllWords();
    }

    @Override
    public String generateNewWord() {
        Random random = new Random();
        String word = words[random.nextInt(0, words.length)];
        return word;
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
}
