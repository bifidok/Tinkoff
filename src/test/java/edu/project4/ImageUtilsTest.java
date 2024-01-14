package edu.project4;

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ImageUtilsTest {
    private static final Path IMAGE = Path.of("src/test/java/edu/project4/image.png");

    @Test
    @DisplayName("Сохранение изображения")
    public void save_shouldCreateImage() {
        Pixel[][] pixels = new Pixel[10][10];
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                pixels[i][j] = new Pixel(0, 0, 0, 0, 0);
            }
        }

        try {
            ImageUtils.save(
                new FractalImage(pixels, 10, 10),
                IMAGE,
                ImageFormat.PNG
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(IMAGE.toFile().exists()).isTrue();
    }

    @AfterAll
    public static void after() {
        if (IMAGE.toFile().exists()) {
            IMAGE.toFile().delete();
        }
    }
}
