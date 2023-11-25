package edu.project4.processor;

import edu.project4.FractalImage;
import edu.project4.Pixel;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class GammaCorrection implements ImageProcessor{
    private final static double GAMMA = 2.2;

    @Override
    public void process(FractalImage image) {
        var pixels = image.pixels();
        double maxNormal = 0.0;
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
        for (int row = 0; row < image.height(); row++) {
            for (int col = 0; col < image.width(); col++) {
                Pixel pixel = pixels[row][col];
                double normal = pixel.normal() / maxNormal;
                int r = (int) (pixel.r() * pow(normal, (1.0 / GAMMA)));
                int g = (int) (pixel.g() * pow(normal, (1.0 / GAMMA)));
                int b = (int) (pixel.b() * pow(normal, (1.0 / GAMMA)));
                pixels[row][col] = new Pixel(r, g, b, pixel.hitCount(), normal);
            }
        }
    }
}
