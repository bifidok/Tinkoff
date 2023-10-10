package edu.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Exercises {
    private final static Logger LOGGER = LogManager.getLogger();

    private Exercises() {
    }

    public static void helloWorld() {
        LOGGER.info("Привет, мир");
    }

    ///////////////////////////////////////////////[1]/////////////////////////////////////////////////////////
    private static final int SECONDS_IN_MINUTES = 60;
    private static final int INTEGER_MAX_DIGITS = 10;

    public static long minutesToSeconds(String value) {
        if (!value.matches("\\d{1," + INTEGER_MAX_DIGITS + "}:\\d{1," + INTEGER_MAX_DIGITS + "}")) {
            return -1;
        }
        String[] minsAndSecs = value.split(":");
        long mins = Long.parseLong(minsAndSecs[0]);
        long secs = Long.parseLong(minsAndSecs[1]);
        if (secs >= SECONDS_IN_MINUTES) {
            return -1;
        }
        return mins * SECONDS_IN_MINUTES + secs;
    }

    //////////////////////////////////////////////////[2]////////////////////////////////////////////////////////
    private static final int TEN = 10;

    public static int countDigits(int value) {
        long temp = value;
        int count = 1;
        while (temp >= TEN || temp <= -TEN) {
            temp /= TEN;
            count++;
        }
        return count;
    }

    /////////////////////////////////////////////////////[3]/////////////////////////////////////////////////////

    public static boolean isNestable(int[] array1, int[] array2) {
        if (array1 == null || array2 == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        int min1 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;

        int min2 = Integer.MAX_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int j : array1) {
            min1 = Math.min(min1, j);
            max1 = Math.max(max1, j);
        }
        for (int j : array2) {
            min2 = Math.min(min2, j);
            max2 = Math.max(max2, j);
        }
        return min1 > min2 && max1 < max2;
    }

    //////////////////////////////////////////////////[4]///////////////////////////////////////////////////////

    public static String fixString(String broken) {
        StringBuilder builder = new StringBuilder(broken.length());
        for (int i = 0; i < broken.length() - 1; i += 2) {
            builder.append(broken.charAt(i + 1)).append(broken.charAt(i));
        }
        if (broken.length() % 2 != 0) {
            builder.append(broken.charAt(broken.length() - 1));
        }
        return builder.toString();
    }

    /////////////////////////////////////////////////[5]//////////////////////////////////////////////////////////////
    private static final int HUNDRED = 100;

    public static boolean isPalindromeDescendant(int value) {
        long number = value;
        if (number < TEN) {
            return true;
        }
        while (number >= TEN) {
            long reversed = reverseNum(number);
            boolean isZeroAtEnd = number % TEN == 0;
            if (!isZeroAtEnd && reversed == number) {
                return true;
            }
            long descendant = 0;
            while (reversed > 0) {
                long a = reversed % TEN;
                reversed /= TEN;
                long b = reversed % TEN;
                reversed /= TEN;
                if (a + b >= TEN) {
                    descendant = descendant * HUNDRED + (a + b);
                } else {
                    descendant = descendant * TEN + (a + b);
                }
            }
            number = descendant;
        }
        return false;
    }

    @SuppressWarnings("ParameterAssignment")

    private static long reverseNum(long num) {
        long reversed = 0;
        while (num > 0) {
            reversed = reversed * TEN + num % TEN;
            num /= TEN;
        }
        return reversed;
    }

    ////////////////////////////////////////////////[6]/////////////////////////////////////////////////////////////////
    @SuppressWarnings("MagicNumber")
    private static int upperBound = 10000;
    @SuppressWarnings("MagicNumber")
    private static int lowerBound = 1000;
    private static final int CONST_CAPREKARA = 6174;
    private static final int CAPREKARA_LENGTH = 4;

    public static int countK(int value) {
        return countK(value, 0);
    }

    @SuppressWarnings("ParameterAssignment")
    private static int countK(int value, int counter) {
        if (value >= upperBound || value <= lowerBound) {
            return -1;
        }
        if (value == CONST_CAPREKARA) {
            return counter;
        }
        int asc = sortNum(value, true);
        int desc = sortNum(value, false);
        return countK(desc - asc, ++counter);
    }

    @SuppressWarnings("ParameterAssignment")
    private static int sortNum(int value, boolean isASC) {
        List<Integer> list = new ArrayList<>();
        int res = 0;
        for (int i = 0; i < CAPREKARA_LENGTH; i++) {
            list.add(value % TEN);
            value /= TEN;
        }
        Collections.sort(list);
        if (!isASC) {
            Collections.reverse(list);
        }
        for (Integer digit : list) {
            res = res * TEN + digit;
        }
        return res;
    }

    ////////////////////////////////////////////[7]//////////////////////////////////////////////////////////////////

    public static int rotateLeft(int n, int shift) {
        int shiftCounter = shift;
        StringBuilder builder = new StringBuilder(Integer.toBinaryString(n));
        if (builder.length() < shiftCounter) {
            shiftCounter %= builder.length();
        }
        builder.append(builder.substring(0, shiftCounter)).delete(0, shiftCounter);
        return Integer.parseInt(builder.toString(), 2);
    }

    public static int rotateRight(int n, int shift) {
        StringBuilder builder = new StringBuilder(Integer.toBinaryString(n));
        int shiftCounter = shift;
        if (builder.length() < shiftCounter) {
            shiftCounter %= builder.length();
        }
        builder.insert(0, builder.substring(builder.length() - shiftCounter, builder.length()))
            .delete(builder.length() - shiftCounter, builder.length());
        return Integer.parseInt(builder.toString(), 2);
    }

    //////////////////////////////////////////////////[8]//////////////////////////////////////////////
    private static final int BOARD_SIZE = 8;
    private static final int[][] POSSIBLE_KNIGHT_MOVEMENT = new int[][] {
        {-1, 2},
        {1, 2},
        {2, 1},
        {2, -1},
        {-1, -2},
        {1, -2},
        {-2, 1},
        {-2, -1},
    };

    public static boolean knightBoardCapture(int[][] chessBoard) {
        if (chessBoard == null || chessBoard.length != BOARD_SIZE
            || Arrays.stream(chessBoard).mapToInt(l -> l.length).anyMatch(s -> s != BOARD_SIZE)) {
            throw new IllegalArgumentException("Input array has incorrect length or null");
        }
        for (int y = 0; y < chessBoard.length; y++) {
            for (int x = 0; x < chessBoard[y].length; x++) {
                if (chessBoard[y][x] == 1) {
                    if (canAttack(chessBoard, x, y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean canAttack(int[][] chessBoard, int x, int y) {
        for (int[] ints : POSSIBLE_KNIGHT_MOVEMENT) {
            int posX = x + ints[0];
            int posY = y + ints[1];
            if (isInBoundaries(posX, posY) && chessBoard[posY][posX] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInBoundaries(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }
}
