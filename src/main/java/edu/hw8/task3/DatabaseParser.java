package edu.hw8.task3;

import java.util.Map;
import java.util.regex.Pattern;

public class DatabaseParser {
    private final static Pattern pattern = Pattern.compile("^(?<name>.+)\\s+(?<hash>.+)$");
    public Map.Entry<String,String> parse(String line){
        var matcher = pattern.matcher(line);
        if(matcher.matches()){
            return Map.entry(matcher.group("hash"),matcher.group("name"));
        }
        return null;
    }
}
