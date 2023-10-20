package edu.project1;

public class GameSession {
    private static final String HIT = "Hit!";
    private static final String MISS = "Missed";
    private static final String WIN = "You won!";
    private static final String LOSE = "You lose!";

    private GameState gameState;
    private int maxAttempts;
    private int curAttempts;
    private Dictionary dictionary;

    public GameSession(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        curAttempts = maxAttempts;
        dictionary = new WordHandler();
    }

    public SessionState startNewSession() {
        gameState = GameState.RUN;
        curAttempts = maxAttempts;
        dictionary.generateNewWord();
        String maskedWord = dictionary.getMaskedWord();
        return new SessionState("", maxAttempts, maxAttempts, maskedWord);
    }

    public GameState checkGameState() {
        if (curAttempts == 0) {
            gameState = GameState.STOP;
        }
        return gameState;
    }

    public SessionState guess(char guess) {
        boolean containsLetter = dictionary.containsLetter(guess);
        String maskedWord = dictionary.getMaskedWord();
        String guessResult;
        if (containsLetter) {
            guessResult = HIT;
            if (dictionary.isAllLettersOpened()) {
                gameState = GameState.STOP;
                guessResult = WIN;
            }
            String newMaskedWord = dictionary.getMaskedWord();
            maskedWord = newMaskedWord;
        } else {
            guessResult = MISS;
            if (curAttempts == 1) {
                gameState = GameState.STOP;
                guessResult = LOSE;
            }
            curAttempts--;
        }
        return new SessionState(guessResult, curAttempts, maxAttempts, maskedWord);
    }
}


