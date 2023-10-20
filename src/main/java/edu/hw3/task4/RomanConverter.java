package edu.hw3.task4;

public class RomanConverter {
    private RomanConverter() {
    }

    private static final String[] ROMAN =
        new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] NUMS = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    public static String convertToRoman(int num) {
        int tempNum = num;
        StringBuilder builder = new StringBuilder();
        if (tempNum < 0) {
            throw new IllegalArgumentException("Roman number system does not have negative numbers");
        }
        for (int i = 0; i < NUMS.length; i++) {
            while (tempNum >= NUMS[i]) {
                builder.append(ROMAN[i]);
                tempNum -= NUMS[i];
            }
        }
        return builder.length() > 0 ? builder.toString() : "Nihil";
    }
}
