package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Exercises {
    private static int counter = 0;

    private final static Logger LOGGER = LogManager.getLogger();

    private Exercises() {
    }

    public static void helloWorld() {
        LOGGER.info("Привет, мир");
    }

    ///////////////////////////////////////////////[1]/////////////////////////////////////////////////////////
    @SuppressWarnings("MagicNumber")
    private static int secondsInMinute = 60;

    public static long minutesToSeconds(String value) {
        String[] minsAndSecs = value.split(":");
        if (minsAndSecs.length != 2) {
            return -1;
        }
        long mins = Long.parseLong(minsAndSecs[0]);
        long secs = Long.parseLong(minsAndSecs[1]);
        if (secs >= secondsInMinute) {
            return -1;
        }
        return mins * secondsInMinute + secs;
    }

    //////////////////////////////////////////////////[2]////////////////////////////////////////////////////////
    @SuppressWarnings("MagicNumber")
    private static int ten = 10;

    public static int countDigits(int value) {
        int temp = value;
        int count = 1;
        if (temp < 0) {
            temp *= -1;
        }
        while (temp >= ten) {
            temp /= ten;
            count++;
        }
        return count;
    }

    /////////////////////////////////////////////////////[3]/////////////////////////////////////////////////////

    public static boolean isNestable(int[] a1, int[] a2) throws NullPointerException {
        if (a1 == null || a2 == null) {
            throw new NullPointerException();
        }
        int min1 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;

        int min2 = Integer.MAX_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int j : a1) {
            min1 = Math.min(min1, j);
            max1 = Math.max(max1, j);
        }
        for (int j : a2) {
            min2 = Math.min(min2, j);
            max2 = Math.max(max2, j);
        }
        return min1 > min2 && max1 < max2;
    }

    //////////////////////////////////////////////////[4]///////////////////////////////////////////////////////

    public static String fixString(String broken) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < broken.length() - 1; i += 2) {
            builder.append(broken.charAt(i + 1)).append(broken.charAt(i));
        }
        if (broken.length() % 2 != 0) {
            builder.append(broken.charAt(broken.length() - 1));
        }
        return builder.toString();
    }

    /////////////////////////////////////////////////[5]//////////////////////////////////////////////////////////////
    @SuppressWarnings("MagicNumber")
    private static int hundred = 100;

    public static boolean isPalindromeDescendant(long value) {
        long number = value;
        while (number >= ten) {
            long reversed = reverseNum(number);
            boolean isZeroAtEnd = number % ten == 0;
            if (!isZeroAtEnd && reversed == number) {
                return true;
            }
            long descendant = 0;
            while (reversed > 0) {
                long a = reversed % ten;
                reversed /= ten;
                long b = reversed % ten;
                reversed /= ten;
                if (a + b >= ten) {
                    descendant = descendant * hundred + (a + b);
                } else {
                    descendant = descendant * ten + (a + b);
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
            reversed = reversed * ten + num % ten;
            num /= ten;
        }
        return reversed;
    }

    ////////////////////////////////////////////////[6]/////////////////////////////////////////////////////////////////
    @SuppressWarnings("MagicNumber")
    private static int constCaprekara = 6174;
    @SuppressWarnings("MagicNumber")
    private static int upperBound = 10000;
    @SuppressWarnings("MagicNumber")
    private static int lowerBound = 1000;
    @SuppressWarnings("MagicNumber")
    private static int caprekaraLength = 4;

    public static int countK(int value) {
        if (value >= upperBound || value <= lowerBound) {
            return -1;
        }
        if (value == constCaprekara) {
            int temp = counter;
            counter = 0;
            return temp;
        }
        int asc = sortNum(value, true);
        int desc = sortNum(value, false);
        counter++;
        return countK(desc - asc);
    }

    @SuppressWarnings("ParameterAssignment")
    private static int sortNum(int value, boolean isASC) {
        List<Integer> list = new ArrayList<>();
        int res = 0;
        for (int i = 0; i < caprekaraLength; i++) {
            list.add(value % ten);
            value /= ten;
        }
        Collections.sort(list);
        if (!isASC) {
            Collections.reverse(list);
        }
        for (Integer digit : list) {
            res = res * ten + digit;
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
        while (shiftCounter > 0) {
            builder.append(builder.charAt(0)).deleteCharAt(0);
            shiftCounter--;
        }
        return Integer.parseInt(builder.toString(), 2);
    }

    public static int rotateRight(int n, int shift) {
        int shiftCounter = shift;
        StringBuilder builder = new StringBuilder(Integer.toBinaryString(n));
        if (builder.length() < shiftCounter) {
            shiftCounter %= builder.length();
        }
        while (shiftCounter > 0) {
            builder.insert(0, builder.charAt(builder.length() - 1)).deleteCharAt(builder.length() - 1);
            shiftCounter--;
        }
        return Integer.parseInt(builder.toString(), 2);
    }

    //////////////////////////////////////////////////[8]//////////////////////////////////////////////
    @SuppressWarnings("MagicNumber")
    private static int boardSize = 8;

    public static boolean knightBoardCapture(int[][] chessBoard) throws AssertionError {
        if (chessBoard == null || chessBoard.length != boardSize || chessBoard[0].length != boardSize) {
            throw new AssertionError();
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
        int[][] waysToMove = new int[][] {
            {x - 1, y + 2},
            {x + 1, y + 2},
            {x + 2, y + 1},
            {x + 2, y - 1},
            {x - 1, y - 2},
            {x + 1, y - 2},
            {x - 2, y + 1},
            {x - 2, y - 1},
        };

        for (int[] ints : waysToMove) {
            int posX = ints[0];
            int posY = ints[1];
            if (isInBoundaries(chessBoard, posX, posY) && chessBoard[posY][posX] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInBoundaries(int[][] chessBoard, int x, int y) {
        return x >= 0 && x < chessBoard[0].length && y >= 0 && y < chessBoard.length;
    }
}
