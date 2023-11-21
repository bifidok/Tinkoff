package edu.hw5.task6;

import java.util.regex.Pattern;

public class SubstringChecker {
    private SubstringChecker() {
    }

    public static boolean isSubstring(String s, String t) {
        Pattern pattern = Pattern.compile(s);
        var matcher = pattern.matcher(t);
        return matcher.find();
    }
}
