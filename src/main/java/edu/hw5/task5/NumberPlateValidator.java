package edu.hw5.task5;

import java.util.regex.Pattern;

public class NumberPlateValidator {
    private final static Pattern PATTERN = Pattern.compile("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}");

    private NumberPlateValidator() {
    }

    public static boolean validate(String number) {
        var matcher = PATTERN.matcher(number);
        return matcher.matches();
    }
}
