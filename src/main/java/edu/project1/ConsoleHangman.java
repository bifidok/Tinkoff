package edu.project1;

public class ConsoleHangman {
    private final static int WORD_LENGTH_LOWER_BOUND = 5;
    private final static int WORD_LENGTH_UPPER_BOUND = 18;
    private final InputOutputSystem inputOutputSystem;
    private final GameSession session;
    private final InputValidator validator;

    public ConsoleHangman(InputOutputSystem inputOutputSystem, GameSession session, int maxAttempts) { // ради тестов
        this.session = session;
        this.inputOutputSystem = inputOutputSystem;
        validator = new InputValidator();
    }

    public void run() {
        Message message = session.startNewSession();
        if (message.getMaskedWord().length() < WORD_LENGTH_LOWER_BOUND
            || message.getMaskedWord().length() > WORD_LENGTH_UPPER_BOUND) {
            throw new IllegalArgumentException("Word has incorrect length");
        }
        while (session.checkGameState() == GameState.RUN) {
            inputOutputSystem.printGameState(message);
            String input = inputOutputSystem.input();

            InputState inputState = validator.validate(input);
            switch (inputState) {
                case END -> {
                    return;
                }
                case RESTART -> {
                    inputOutputSystem.clearConsole();
                    session.startNewSession();
                }
                case CHARACTER -> {
                    message = session.guess(input.charAt(0));
                    inputOutputSystem.printGuessResult(message);
                }
                default -> {

                }
            }
        }
    }
}
