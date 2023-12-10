package edu.project3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LogAnalyzerTest {
    private static final String FILE_PATH = "C:\\Users\\striz\\IdeaProjects\\project-template\\nginxLogs.txt";
    private final LogAnalyzer analyzer = new LogAnalyzer();
    private static File file;

    @BeforeAll
    public static void init() {
        file = new File(FILE_PATH);
        String logs =
            "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"\n" +
                "93.180.71.3 - - [17/May/2015:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"\n" +
                "80.91.33.133 - - [17/May/2015:08:05:24 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.17)\"";
        try {
            Files.writeString(file.toPath(), logs, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Test
    @DisplayName("Сбор статистики базовый тест")
    public void analyze_shouldAnalyzeFile() {
        Statistic statistic = analyzer.analyze(file, null, null);

        assertThat(statistic).isNotNull();
        assertThat(statistic.fileName()).isEqualTo(file.getName());
        assertThat(statistic.logsCount()).isNotEqualTo(0);
        assertThat(statistic.dateToCount().size()).isNotEqualTo(0);
        assertThat(statistic.resourceToRequestCount().size()).isNotEqualTo(0);
        assertThat(statistic.responseCodeToCount().size()).isNotEqualTo(0);
        assertThat(statistic.useragentToCount().size()).isNotEqualTo(0);
    }

    @Test
    @DisplayName("Сбор статистики пустой файл")
    public void analyze_shouldReturnNull_whenEmptyFile() {
        Statistic statistic = analyzer.analyze(new File("empty.txt"), null, null);

        assertThat(statistic).isNull();
    }

}
