package edu.hw9.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileSizeTaskTest {
    @Test
    @DisplayName("Поиск файлов размером 100")
    public void compute_whenSizeisEqualTo100(){
        int requiredSize = 100;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FileSizeTask fileSizeTask = new FileSizeTask(Path.of(""),requiredSize);
        forkJoinPool.execute(fileSizeTask);

        List<Path> fileListExpected;
        try {
            fileListExpected = Files.find(
                    Path.of(""),
                    Integer.MAX_VALUE,
                    (p, a) -> a.isRegularFile() && p.toFile().length() == requiredSize)
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Path> fileListActual = fileSizeTask.join();

        assertThat(fileListExpected.size()).isEqualTo(fileListActual.size());
    }
}
