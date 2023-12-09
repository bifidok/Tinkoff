package edu.project4.processor;

import edu.project4.FractalImage;
import edu.project4.Pixel;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class GammaCorrection implements ImageProcessor {
    private final double gamma;
    private double maxNormal;

    public GammaCorrection(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(FractalImage image) {
        maxNormal = 0;
        setNormals(image);
        changeBrightness(image);
    }

    private void setNormals(FractalImage image) {
        var pixels = image.pixels();
        for (int row = 0; row < image.height(); row++) {
            for (int col = 0; col < image.width(); col++) {
                if (pixels[row][col].hitCount() != 0) {
                    Pixel pixel = pixels[row][col];
                    double normal = log10(pixel.hitCount());
                    pixels[row][col] = new Pixel(pixel.r(), pixel.g(), pixel.b(), pixel.hitCount(), normal);
                    if (normal > maxNormal) {
                        maxNormal = normal;
                    }
                }
            }
        }
    }

    private void changeBrightness(FractalImage image) {
        var pixels = image.pixels();
        for (int row = 0; row < image.height(); row++) {
            for (int col = 0; col < image.width(); col++) {
                Pixel pixel = pixels[row][col];
                if (pixel.hitCount() != 0) {
                    double normal = pixel.normal() / maxNormal;
                    int r = (int) (pixel.r() * pow(normal, (1.0 / gamma)));
                    int g = (int) (pixel.g() * pow(normal, (1.0 / gamma)));
                    int b = (int) (pixel.b() * pow(normal, (1.0 / gamma)));
                    pixels[row][col] = new Pixel(r, g, b, pixel.hitCount(), normal);
                }
            }
        }
    }
}
