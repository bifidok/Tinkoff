package edu.project1;

public class GameSession {
    private static final String HIT = "Hit!";
    private static final String MISS = "Missed";
    private static final String WIN = "You won!";
    private static final String LOSE = "You lose!";

    private GameState gameState;
    private int maxAttempts;
    private int curAttempts;
    private WordHandler wordHandler;

    public GameSession(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        curAttempts = maxAttempts;
        wordHandler = new WordHandler();
    }

    public SessionState startNewSession() {
        gameState = GameState.RUN;
        curAttempts = maxAttempts;
        wordHandler.generateNewWord();
        String maskedWord = wordHandler.getMaskedWord();
        return new SessionState("", maxAttempts, maxAttempts, maskedWord);
    }

    public GameState checkGameState() {
        if (curAttempts == 0) {
            gameState = GameState.STOP;
        }
        return gameState;
    }

    public SessionState guess(char guess) {
        boolean containsLetter = wordHandler.containsLetter(guess);
        String maskedWord = wordHandler.getMaskedWord();
        String guessResult;
        if (containsLetter) {
            guessResult = HIT;
            if (wordHandler.isAllLettersOpened()) {
                gameState = GameState.STOP;
                guessResult = WIN;
            }
            String newMaskedWord = wordHandler.getMaskedWord();
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


