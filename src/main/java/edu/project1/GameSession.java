package edu.project1;

import java.util.Arrays;

public class GameSession {
    private GameState gameState;
    private int maxAttempts;
    private char[] attemptsInput;
    private int curAttempts;
    private String maskedWord;
    private Message message;
    private Dictionary dictionary;
    private static final String HIT = "Hit!";
    private static final String MISS = "Missed";
    private static final String INVALID = "Your input invalid";
    private static final String WIN = "You won!";
    private static final String LOSE = "You lose!";

    public GameSession(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        curAttempts = maxAttempts;
        message = new Message("", "", curAttempts, maxAttempts);
        attemptsInput = new char[maxAttempts];
        dictionary = new Dictionary();
    }

    public Message startNewSession() {
        gameState = GameState.RUN;
        dictionary.generateNewWord();
        resetAll();
        return message;
    }

    public Message guess(char guess) {
        // повторный ввод буквы
        if (!Character.isLetter(guess)) {
            message.setGuessResult(INVALID);
            return message;
        }
        boolean containsLetter = dictionary.containsLetter(guess);
        if (containsLetter) {
            message.setGuessResult(HIT);
            if (dictionary.isAllLettersOpened()) {
                gameState = GameState.STOP;
                message.setGuessResult(WIN);
            }
            String newMaskedWord = dictionary.getMaskedWord();
            message.setMaskedWord(newMaskedWord);
        } else {
            message.setGuessResult(MISS);
            if (curAttempts == 1) {
                gameState = GameState.STOP;
                message.setGuessResult(LOSE);
            }
            curAttempts--;
            message.setCurAttempts(curAttempts);
        }
        return message;
    }

    private void resetAll() {
        curAttempts = maxAttempts;
        Arrays.fill(attemptsInput, '*');
        message.setCurAttempts(maxAttempts);
        message.setMaskedWord(dictionary.getMaskedWord());
    }

    public GameState checkGameState() {
        return gameState;
    }

    public String getMaskedWord() {
        return maskedWord;
    }
}

enum GameState {
    RUN,
    STOP
}
