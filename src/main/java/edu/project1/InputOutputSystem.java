package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputOutputSystem {
    private final static Logger LOGGER = LogManager.getLogger();
    private final Scanner scanner;
    private final InputParser inputParser;

    public InputOutputSystem() {
        scanner = new Scanner(System.in);
        inputParser = new InputParser();
    }

    public String input() {
        String input = scanner.nextLine();
        return input;
    }

    public InputState parseInput(String input) {
        return inputParser.parse(input);
    }

    public void printMessage(String message) {
        LOGGER.info(message);
    }

    public void printGuessResult(SessionState state) {
        LOGGER.info(state.guessResult());
    }

    public void printGameState(SessionState state) {
        LOGGER.info("The word: " + state.maskedWord());
        LOGGER.info("You have " + state.curAttempts() + " of "
            + state.maxAttempts() + " attempts");
        LOGGER.info("Guess a letter: ");
    }

    public void clearConsole() {
        LOGGER.info("\n\n\n\n\n\n");
    }
}

