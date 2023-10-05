package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Exercises {
    private static int counter = 0;

    private final static Logger LOGGER = LogManager.getLogger();

    public static void helloWorld() {
        LOGGER.info("Привет, мир");
    }

    ///////////////////////////////////////////////[1]/////////////////////////////////////////////////////////

    public static long minutesToSeconds(String value) {
        String[] minsAndSecs = value.split(":");
        if (minsAndSecs.length != 2) {
            return -1;
        }
        long mins = Long.parseLong(minsAndSecs[0]);
        long secs = Long.parseLong(minsAndSecs[1]);
        if (secs > 59) {
            return -1;
        }
        return mins * 60 + secs;
    }

    //////////////////////////////////////////////////[2]////////////////////////////////////////////////////////

    public static int countDigits(int value) {
        int count = 1;
        if (value < 0) {
            value *= -1;
        }
        while (value > 9) {
            value /= 10;
            count++;
        }
        return count;
    }
    /////////////////////////////////////////////////////[3]/////////////////////////////////////////////////////

    public static boolean isNestable(int[] a1, int[] a2) {
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

    public static boolean isPalindromeDescendant(long value) {
        /////
        //if(String.valueOf(value).length() % 2 != 0) return false;
        while (value > 9) {
            long reversed = reverseNum(value);
            boolean isZeroAtEnd = value % 10 == 0;
            if (!isZeroAtEnd && reversed == value) {
                return true;
            }
            long descendant = 0;
            while (reversed > 0) {
                long a = reversed % 10;
                reversed /= 10;
                long b = reversed % 10;
                reversed /= 10;
                if (a + b > 9) {
                    descendant = descendant * 100 + (a + b);
                } else {
                    descendant = descendant * 10 + (a + b);
                }
            }
            value = descendant;
        }
        return false;
    }

    private static long reverseNum(long num) {
        long reversed = 0;
        while (num > 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }
        return reversed;
    }

    ////////////////////////////////////////////////[6]/////////////////////////////////////////////////////////////////
    public static int countK(int value) {
        if (value > 9999 || value < 1001) {
            return -1;
        }
        if (value == 6174) {
            int temp = counter;
            counter = 0;
            return temp;
        }
        int asc = sortNum(value, true);
        int desc = sortNum(value, false);
        counter++;
        return countK(desc - asc);
    }

    private static int sortNum(int value, boolean isASC) {
        List<Integer> list = new ArrayList<>();
        int res = 0;
        for (int i = 0; i < 4; i++) {
            list.add(value % 10);
            value /= 10;
        }
        Collections.sort(list);
        if (!isASC) {
            Collections.reverse(list);
        }
        for (Integer digit : list) {
            res = res * 10 + digit;
        }
        return res;
    }

    ////////////////////////////////////////////[7]//////////////////////////////////////////////////////////////////

    public static int rotateLeft(int n, int shift) {
        StringBuilder builder = new StringBuilder(Integer.toBinaryString(n));
        if (builder.length() < shift) {
            shift %= builder.length();
        }
        while (shift > 0) {
            builder.append(builder.charAt(0)).deleteCharAt(0);
            shift--;
        }
        return Integer.parseInt(builder.toString(), 2);
    }

    public static int rotateRight(int n, int shift) {
        StringBuilder builder = new StringBuilder(Integer.toBinaryString(n));
        if (builder.length() < shift) {
            shift %= builder.length();
        }
        while (shift > 0) {
            builder.insert(0, builder.charAt(builder.length() - 1)).deleteCharAt(builder.length() - 1);
            shift--;
        }
        return Integer.parseInt(builder.toString(), 2);
    }

    //////////////////////////////////////////////////[8]/////////////////////////////////////////////////////////////////////

    public static boolean knightBoardCapture(int[][] chessBoard) {
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
