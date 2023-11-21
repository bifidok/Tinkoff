package edu.hw8.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseReader {
    private final DatabaseParser parser;

    public DatabaseReader() {
        parser = new DatabaseParser();
    }

    public Map<String, String> read(Path path) {
        Map<String, String> database = new HashMap<>();
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        for (String line : lines) {
            var entry = parser.parse(line);
            if (entry != null) {
                database.put(entry.getKey(), entry.getValue());
            }
        }
        return database;
    }
}
