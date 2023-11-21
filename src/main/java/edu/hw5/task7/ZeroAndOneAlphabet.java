package edu.hw5.task7;

import java.util.regex.Pattern;

public class ZeroAndOneAlphabet {
    private final static Pattern PATTERN = Pattern.compile("[10]{2}0[10]*");
    private final static Pattern PATTERN1 = Pattern.compile("^(0|1)[01]*(0|1)$");
    private final static Pattern PATTERN2 = Pattern.compile("(0|1){1,3}");

    private ZeroAndOneAlphabet() {
    }

    public static boolean atLeastThreeSymbols(String value) {
        var matcher = PATTERN.matcher(value);
        return matcher.matches();
    }

    public static boolean sameSymbolAtStartAndEnd(String value) {
        var matcher = PATTERN1.matcher(value);
        if (matcher.matches()) {
            return matcher.group(1).equals(matcher.group(2));
        }
        return false;
    }

    public static boolean lengthFromOneToThree(String value) {
        var matcher = PATTERN2.matcher(value);
        return matcher.matches();
    }
}
