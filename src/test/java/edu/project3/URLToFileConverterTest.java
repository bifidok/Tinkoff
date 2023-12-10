package edu.project3;

import java.io.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class URLToFileConverterTest {

    @Test
    @DisplayName("Валидный url")
    public void convert_shouldConvertFromURL() {
        String filePath = "C:\\Users\\striz\\IdeaProjects\\project-template\\nginxLogs.txt";
        String urlPath =
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        File file = URLToFileConverter.convert(urlPath);

        assertThat(file).isNotNull();
        assertThat(file.getPath()).isEqualTo(filePath);
    }

    @Test
    @DisplayName("Невалидный url")
    public void convert_shouldThrowException() {
        String urlPath = "adsf";
        Throwable thrown = catchThrowable(() -> {
            URLToFileConverter.convert(urlPath);
        });

        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }
}
