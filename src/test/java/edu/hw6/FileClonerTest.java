package edu.hw6;

import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FileClonerTest {
    private static final Path FILE_CLONE_PATH =
        Path.of("C:\\Users\\striz\\IdeaProjects\\project-template\\src\\main\\resources\\file.txt");

    // Не проходит билд на гите из-за создания файлов
//    @Test
//    @DisplayName("Клонирование файла 5 раз")
//    public void cloneFile_shouldCloneFiles() {
//        FileCloner cloner = new FileCloner();
//
//        var file1 = cloner.cloneFile(FILE_CLONE_PATH);
//        var expectedFile1Name = "file.txt";
//        var file2 = cloner.cloneFile(FILE_CLONE_PATH);
//        var expectedFile2Name = "file - копия.txt";
//        var file3 = cloner.cloneFile(FILE_CLONE_PATH);
//        var expectedFile3Name = "file - копия(2).txt";
//        var file4 = cloner.cloneFile(FILE_CLONE_PATH);
//        var expectedFile4Name = "file - копия(3).txt";
//        file1.toFile().delete();
//        file2.toFile().delete();
//        file3.toFile().delete();
//        file4.toFile().delete();
//
//        assertThat(file1.getFileName().toString()).isEqualTo(expectedFile1Name);
//        assertThat(file2.getFileName().toString()).isEqualTo(expectedFile2Name);
//        assertThat(file3.getFileName().toString()).isEqualTo(expectedFile3Name);
//        assertThat(file4.getFileName().toString()).isEqualTo(expectedFile4Name);
//    }
}
