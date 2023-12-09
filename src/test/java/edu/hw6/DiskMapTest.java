package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiskMapTest {
    private static final Path FILE_DISK_MAP_PATH =
        Path.of("C:\\Users\\striz\\IdeaProjects\\project-template\\src\\main\\resources\\Dictionary.txt");
    private static DiskMap diskMap;

    @BeforeAll
    public static void init() {
        diskMap = new DiskMap(FILE_DISK_MAP_PATH.toString());
    }

    @AfterAll
    public static void teardown() {
        FILE_DISK_MAP_PATH.toFile().delete();
    }

    @Test
    @DisplayName("Проверка добавления")
    public void diskMap_shouldSaveEntries() {
        diskMap.put("1", "1");
        diskMap.put("2", "2");
        diskMap.save();

        try {
            var lines = Files.readAllLines(FILE_DISK_MAP_PATH);
            for (String line : lines) {
                String[] keyValue = line.split("=");
                assertThat(diskMap.containsKey(keyValue[0]));
                assertThat(diskMap.containsValue(keyValue[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Проверка удаления")
    public void diskMap_shouldDeleteEntries() {
        diskMap.put("1", "1");
        diskMap.put("2", "2");
        diskMap.save();
        diskMap.remove("1");
        diskMap.save();

        try {
            var lines = Files.readAllLines(FILE_DISK_MAP_PATH);
            for (String line : lines) {
                String[] keyValue = line.split("=");
                assertThat(diskMap.containsKey(keyValue[0]));
                assertThat(diskMap.containsValue(keyValue[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
