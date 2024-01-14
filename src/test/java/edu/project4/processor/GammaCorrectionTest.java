package edu.project4.processor;

import edu.project4.FractalImage;
import edu.project4.Pixel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GammaCorrectionTest {
    @Test
    @DisplayName("Гамма-коррекция")
    public void process_shouldDecreasePixelBrightness(){
        int width = 100;
        int height = 100;
        ImageProcessor correction = new GammaCorrection(2.2);
        Random random = new Random();
        var before = new Pixel[height][width];
        var after = new Pixel[height][width];
        for(int i = 0; i < before.length; i++){
            for(int j = 0; j < before[i].length; j++){
                int hitCountRandom = random.nextInt(1,10);
                before[i][j] = new Pixel(10,10,10,hitCountRandom,0);
                after[i][j] = new Pixel(10,10,10,hitCountRandom,0);
            }
        }

        correction.process(new FractalImage(after,width,height));

        for(int i = 0; i < after.length;i++){
            for (int j = 0; j < after[i].length;j++){
                assertThat(before[i][j].r() >= after[i][j].r()).isTrue();
                assertThat(before[i][j].g() >= after[i][j].g()).isTrue();
                assertThat(before[i][j].b() >= after[i][j].b()).isTrue();
            }
        }
    }
}
