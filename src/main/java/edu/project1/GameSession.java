package edu.project1;

public class GameSession {
    private GameState gameState;
    private int maxAttempts;
    private int curAttempts;
    private Message message;
    private Dictionary dictionary;
    private static final String HIT = "Hit!";
    private static final String MISS = "Missed";
    private static final String WIN = "You won!";
    private static final String LOSE = "You lose!";

    public GameSession(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        curAttempts = maxAttempts;
        message = new Message("", "", curAttempts, maxAttempts);
        dictionary = new WordHandler();
    }

    public Message startNewSession() {
        gameState = GameState.RUN;
        dictionary.generateNewWord();
        resetAll();
        return message;
    }

    public GameState checkGameState() {
        return gameState;
    }

    public Message guess(char guess) {
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
        message.setCurAttempts(maxAttempts);
        message.setMaskedWord(dictionary.getMaskedWord());
    }
}


