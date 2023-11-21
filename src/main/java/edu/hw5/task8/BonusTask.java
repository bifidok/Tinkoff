package edu.hw5.task8;

import java.util.regex.Pattern;

public class BonusTask {
    private final static Pattern PATTERN1 = Pattern
        .compile("(0|1)(00|11|01|10)*"); // нечетной длины
    private final static Pattern PATTERN2 = Pattern
        .compile("(0(00|11|01|10)*)|((11|10)(00|11|01|10)*)"); // начинается с 0 и имеет нечетную длину,
    // или начинается с 1 и имеет четную длину
    private final static Pattern PATTERN3 = Pattern
        .compile("^(1*01*01*01*)+$"); // количество 0 кратно 3
    private final static Pattern PATTERN4 = Pattern
        .compile("^(?!11$|111$)[01]+$"); // любая строка, кроме 11 или 111
    private final static Pattern PATTERN5 = Pattern
        .compile("^(1{1}0{1}|1{1})+$"); //каждый нечетный символ равен 1
    private final static Pattern PATTERN6 = Pattern
        .compile("^(?=.*0.*0)(?!.*1.*1)[01]+$"); //содержит не менее двух 0 и не более одной 1
    private final static Pattern PATTERN7 = Pattern
        .compile("^(?!.*11)[01]+$"); // нет последовательных 1

    private BonusTask() {
    }

    public static boolean oddLength(String value) {
        var matcher = PATTERN1.matcher(value);
        return matcher.matches();
    }

    public static boolean startsWithZeroAndHasOddLengthOrWithOneAndEvenLength(String value) {
        var matcher = PATTERN2.matcher(value);
        return matcher.matches();
    }

    public static boolean zeroCountIsMultipleOfThree(String value) {
        var matcher = PATTERN3.matcher(value);
        return matcher.matches();
    }

    public static boolean exceptTwoOrThreeOnes(String value) {
        var matcher = PATTERN4.matcher(value);
        return matcher.matches();
    }

    public static boolean everyOddIsOne(String value) {
        var matcher = PATTERN5.matcher(value);
        return matcher.matches();
    }

    public static boolean twoZeroesOneOne(String value) {
        var matcher = PATTERN6.matcher(value);
        return matcher.matches();
    }

    public static boolean noConsistentOnes(String value) {
        var matcher = PATTERN7.matcher(value);
        return matcher.matches();
    }
}
