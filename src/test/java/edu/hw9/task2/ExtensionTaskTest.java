package edu.hw9.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExtensionTaskTest {
    @Test
    @DisplayName("Поиск файлов .java")
    public void compute_shouldFindFilesWithRequiredExtension_whenJava(){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ExtensionTask extensionTask = new ExtensionTask(Path.of(""),"java");
        forkJoinPool.execute(extensionTask);

        List<Path> fileListExpected;
        try {
            fileListExpected = Files.find(
                Path.of(""),
                    Integer.MAX_VALUE,
                    (p, a) -> a.isRegularFile() && p.getFileName().toString().endsWith(".java"))
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Path> fileListActual = extensionTask.join();

        assertThat(fileListExpected.size()).isEqualTo(fileListActual.size());
    }

    @Test
    @DisplayName("Поиск файлов .xml")
    public void compute_shouldFindFilesWithRequiredExtension_whenXml(){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ExtensionTask extensionTask = new ExtensionTask(Path.of(""),"xml");
        forkJoinPool.execute(extensionTask);

        List<Path> fileListExpected;
        try {
            fileListExpected = Files.find(
                Path.of(""),
                    Integer.MAX_VALUE,
                    (p, a) -> a.isRegularFile() && p.getFileName().toString().endsWith(".xml"))
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Path> fileListActual = extensionTask.join();

        assertThat(fileListExpected.size()).isEqualTo(fileListActual.size());
    }
}
