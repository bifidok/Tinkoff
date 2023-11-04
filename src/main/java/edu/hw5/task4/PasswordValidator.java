package edu.hw5.task4;

import java.util.regex.Pattern;

public class PasswordValidator {
    private final static Pattern PATTERN = Pattern.compile("[~!@#$%^&*|]+");

    private PasswordValidator() {
    }

    public static boolean validate(String password) {
        var matcher = PATTERN.matcher(password);
        return matcher.find();
    }
}
