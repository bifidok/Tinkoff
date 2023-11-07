package edu.project3.output;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StatisticPrinter {
    private final static Logger LOGGER = LogManager.getLogger();

    private StatisticPrinter() {
    }

    public static void print(String path) {
        try {
            Files.readAllLines(Path.of(path))
                .forEach(LOGGER::info);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
