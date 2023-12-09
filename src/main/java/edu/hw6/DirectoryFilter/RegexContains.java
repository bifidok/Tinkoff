package edu.hw6.DirectoryFilter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class RegexContains extends AbstractFilter {
    private Pattern fileNamePattern;

    public RegexContains(String regex) {
        fileNamePattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        var matcher = fileNamePattern.matcher(entry.toString());
        return matcher.matches() && (next != null ? next.accept(entry) : true);
    }
}
