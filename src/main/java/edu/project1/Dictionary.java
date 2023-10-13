package edu.project1;

public interface Dictionary {
    void generateNewWord();

    boolean containsLetter(char letter);

    boolean isAllLettersOpened();

    String getMaskedWord();

}
