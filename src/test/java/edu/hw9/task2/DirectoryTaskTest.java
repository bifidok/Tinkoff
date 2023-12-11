package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DirectoryTaskTest {
    @Test
    @DisplayName("Поиск директорий с количеством файлов > 1000")
    public void compute_shouldFindRequiredDirectories() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<Path> requiredDirectories = new ArrayList<>();
        DirectoryTask directoryTask = new DirectoryTask(Path.of(""), requiredDirectories);
        forkJoinPool.execute(directoryTask);

        List<Path> fileListExpected;
        try {
            fileListExpected = Files.find(Path.of(""), Integer.MAX_VALUE, (p, a) -> a.isRegularFile())
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Path> fileListActual = directoryTask.join();

        assertThat(fileListExpected.size()).isEqualTo(fileListActual.size());
        if (fileListExpected.size() > 1000) {
            assertThat(requiredDirectories.size()).isNotEqualTo(0);
        } else {
            assertThat(requiredDirectories.size()).isEqualTo(0);
        }
    }
}
