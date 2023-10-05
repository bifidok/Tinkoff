package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        //Exercises.helloWorld();
        //LOGGER.info(Exercises.minutesToSeconds("12:44"));
        //LOGGER.info(Exercises.countDigits(4666));
        //LOGGER.info(Exercises.isNestable(new int [] {1,2,3,4}, new int [] {0, 6}));
        //LOGGER.info(Exercises.fixString("123456"));
        LOGGER.info(Exercises.isPalindromeDescendant(123310));
        //LOGGER.info(Exercises.countK(3524));
        //LOGGER.info(Exercises.rotateLeft(17,2));
        //LOGGER.info(Exercises.rotateRight(8,1));
//        LOGGER.info(Exercises.knightBoardCapture(new int[][] {
//            {0, 0, 0, 1, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 1, 0, 0, 0, 1, 0, 0},
//            {0, 0, 0, 0, 1, 0, 1, 0},
//            {0, 1, 0, 0, 0, 1, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 1, 0, 0, 0, 0, 0, 1},
//            {0, 0, 0, 0, 1, 0, 0, 0}
//        }));
    }
}
