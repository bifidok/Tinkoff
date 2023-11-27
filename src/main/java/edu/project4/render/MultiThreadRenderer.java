package edu.project4.render;

import edu.project4.AffineFactorContainer;
import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.transformation.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadRenderer implements Renderer {
    private static final Random random = new Random();

    private final int threadCount;

    public MultiThreadRenderer(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public FractalImage render(FractalFlameConfiguration configuration, List<Transformation> variations) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        Transformation transformation = variations.get(random.nextInt(0, variations.size()));
        int xResolution = configuration.width();
        int yResolution = configuration.height();
        var pixels = createInitialPixels(xResolution, yResolution);
        var affineFactors = createFactors(5);
        AtomicInteger sample = new AtomicInteger(0);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new RenderThread(configuration, affineFactors, transformation, pixels, sample));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        return new FractalImage(pixels.toArray(), xResolution, yResolution);
    }

    private AffineFactorContainer[] createFactors(int count) {
        AffineFactorContainer[] factors = new AffineFactorContainer[count];
        for (int i = 0; i < factors.length; i++) {
            factors[i] = new AffineFactorContainer(
                randomFactor(),
                randomFactor(),
                randomFactor(),
                randomFactor(),
                randomFactor(),
                randomFactor(),
                randomColor(),
                randomColor(),
                randomColor()
            );
        }
        return factors;
    }

    private ConcurrentPixelTwoDimensionalArray createInitialPixels(int width, int height) {
        Pixel[][] pixels = new Pixel[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixels[row][col] = new Pixel(0, 0, 0, 0, 0);
            }
        }
        return new ConcurrentPixelTwoDimensionalArray(pixels);
    }

    private double randomFactor() {
        return random.nextDouble(-1, 1);
    }

    private int randomColor() {
        return random.nextInt(64, 256) + 64;
    }
}
