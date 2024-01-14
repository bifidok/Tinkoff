package edu.project3.output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StatisticPrinter {

    private StatisticPrinter() {
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void print(String filePath) {
        try {
            Files.readAllLines(Path.of(filePath))
                .forEach(System.out::println);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
