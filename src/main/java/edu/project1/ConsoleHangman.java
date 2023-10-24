package edu.project1;

public class ConsoleHangman {
    private final static int WORD_LENGTH_LOWER_BOUND = 5;
    private final static int WORD_LENGTH_UPPER_BOUND = 18;
    private final InputOutputSystem inputOutputSystem;
    private final GameSession session;

    public ConsoleHangman(InputOutputSystem inputOutputSystem, GameSession session, int maxAttempts) { // ради тестов
        this.session = session;
        this.inputOutputSystem = inputOutputSystem;
    }

    public void run() {
        SessionState sessionState = session.startNewSession();
        if (sessionState.maskedWord().length() < WORD_LENGTH_LOWER_BOUND
            || sessionState.maskedWord().length() > WORD_LENGTH_UPPER_BOUND) {
            throw new IllegalArgumentException("Word has incorrect length");
        }
        while (session.checkGameState() == GameState.RUN) {
            inputOutputSystem.printGameState(sessionState);
            String input = inputOutputSystem.input();
            InputState inputState = inputOutputSystem.parseInput(input);

            switch (inputState) {
                case END -> {
                    return;
                }
                case RESTART -> {
                    inputOutputSystem.clearConsole();
                    sessionState = session.startNewSession();
                }
                case CHARACTER -> {
                    sessionState = session.guess(input.charAt(0));
                    inputOutputSystem.printGuessResult(sessionState);
                }
                default -> {
                    inputOutputSystem.printMessage("Dont understand your input!");
                }
            }
        }
    }
}
