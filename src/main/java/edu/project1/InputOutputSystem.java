package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputOutputSystem {
    private final static Logger LOGGER = LogManager.getLogger();
    private final Scanner scanner;

    public InputOutputSystem() {
        scanner = new Scanner(System.in);
    }

    public String input() {
        String input = scanner.nextLine();
        return input;
    }

    public void printGuessResult(Message message) {
        LOGGER.info(message.getGuessResult());
    }

    public void printGameState(Message message) {
        LOGGER.info("The word: " + message.getMaskedWord());
        LOGGER.info("You have " + message.getCurAttempts() + " of "
            + message.getMaxAttempts() + " attempts");
        LOGGER.info("Guess a letter: ");
    }

    public void clearConsole() {
        LOGGER.info("\n\n\n\n\n\n");
    }
}

