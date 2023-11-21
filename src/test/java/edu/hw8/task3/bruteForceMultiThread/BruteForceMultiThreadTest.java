package edu.hw8.task3.bruteForceMultiThread;

import edu.hw8.task3.BruteForce;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BruteForceMultiThreadTest {
    private static Path hackedDatabase;
    private static List<String> users;
    private static int linesCount;

    @BeforeAll
    public static void init() {
        hackedDatabase = Path.of("src/main/resources/database");
        users = new ArrayList<>();
        if (!hackedDatabase.toFile().exists()) {
            try {
                File file = hackedDatabase.toFile();
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            var lines = Files.readAllLines(hackedDatabase);
            linesCount = lines.size();
            for (String line : lines) {
                String[] userAndPassword = line.split(" ");
                users.add(userAndPassword[0]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Многопоточный брутфорс")
    public void start_shouldFindAllLines() {
        BruteForceMultiThread bruteForce = new BruteForceMultiThread(4,5);
        int lines = -1;
        try {
            lines = Files.readAllLines(hackedDatabase).size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, String> bruteForced = bruteForce.start(hackedDatabase);

        assertThat(bruteForced.size()).isEqualTo(lines);
        for(String user : users){
            assertThat(bruteForced.containsKey(user)).isTrue();
        }
    }
}
