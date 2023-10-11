package edu.project1;

import java.util.Scanner;

public class ConsoleHangman {
    private InputOutputSystem inputOutputSystem;
    private GameSession session;
    private final static String restart = "rr";
    private final static String end = "end";

    public ConsoleHangman(int maxAttempts) {
        session = new GameSession(maxAttempts);
        inputOutputSystem = new InputOutputSystem();
    }

    public void run() {
        Message message = session.startNewSession();
        while (session.checkGameState() == GameState.RUN) {
            inputOutputSystem.output("The word: " + message.getMaskedWord());
            inputOutputSystem.output("You have " + message.getCurAttempts() + " of "
                + message.getMaxAttempts() + " attempts");
            inputOutputSystem.output("Guess a letter: ");
            String input = inputOutputSystem.input();
            if (input.length() != 1) {
                switch (input) {
                    case restart:
                        System.out.println("\n\n\n\n");
                        session.startNewSession();
                        continue;
                    case end:
                        return;
                    default:
                        continue;
                }
            }
            message = session.guess(input.charAt(0));
            inputOutputSystem.output(message.getGuessResult());
        }
    }

}
