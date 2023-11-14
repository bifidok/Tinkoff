package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OutputStreamChainTest {
    private final static String QUOTE = "Programming is learned by writing programs. ― Brian Kernighan";
    private static final Path PATH =
        Path.of("C:\\Users\\striz\\IdeaProjects\\project-template\\src\\main\\resources\\file.txt");

    @Test
    @DisplayName("Базовый тест")
    public void write() {
        OutputStreamChain.write(PATH);

        try {
            var lines = Files.readAllLines(PATH);
            PATH.toFile().delete();
            assertThat(lines.get(0)).isEqualTo(QUOTE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
