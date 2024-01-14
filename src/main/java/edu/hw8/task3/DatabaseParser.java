package edu.hw8.task3;

import java.util.Map;
import java.util.regex.Pattern;

public class DatabaseParser {
    private final static Pattern LOGIN_HASH_PATTERN =
        Pattern.compile("^(?<name>[a-zA-Z0-9\\.]+)\\s+(?<hash>[a-fA-F0-9]{32})$");

    public Map.Entry<String, String> parse(String line) {
        var matcher = LOGIN_HASH_PATTERN.matcher(line);
        if (matcher.matches()) {
            return Map.entry(matcher.group("hash"), matcher.group("name"));
        }
        return null;
    }
}
