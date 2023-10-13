package edu.project1;

public class ConsoleHangman {
    private final static int WORD_LENGTH_LOWER_BOUND = 5;
    private final static int WORD_LENGTH_UPPER_BOUND = 18;
    private final InputOutputSystem inputOutputSystem;
    private final GameSession session;
    private final InputValidator validator;

    public ConsoleHangman(int maxAttempts) {
        session = new GameSession(maxAttempts);
        inputOutputSystem = new InputOutputSystem();
        validator = new InputValidator();
    }

    public void run() {
        Message message = session.startNewSession();
        if(message.getMaskedWord().length() < WORD_LENGTH_LOWER_BOUND
        || message.getMaskedWord().length() > WORD_LENGTH_UPPER_BOUND){
            return;
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
                default -> {
                    message = session.guess(input.charAt(0));
                    inputOutputSystem.printGuessResult(message);
                }
            }
        }
    }

}
