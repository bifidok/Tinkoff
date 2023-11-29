package edu.project4.render;

import edu.project4.AffineFactorContainer;
import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadRenderer implements Renderer {
    private final static Random RANDOM = new Random();
    private final static int AFFINE_FACTORS = 5;
    private final static int MAX_WAITING_MINUTES_FOR_RENDERER = 3;

    private int threadCount;

    public MultiThreadRenderer(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public FractalImage render(FractalFlameConfiguration configuration, List<Transformation> variations) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        Transformation transformation = variations.get(RANDOM.nextInt(0, variations.size()));
        int xResolution = configuration.width();
        int yResolution = configuration.height();
        var pixels = createInitialPixels(xResolution, yResolution);
        var affineFactors = createFactors(AFFINE_FACTORS);
        AtomicInteger sample = new AtomicInteger(0);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(new RenderThread(configuration, affineFactors, transformation, pixels, sample));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(MAX_WAITING_MINUTES_FOR_RENDERER, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
        return RANDOM.nextDouble(-1, 1);
    }

    @SuppressWarnings("MagicNumber")
    private int randomColor() {
        return RANDOM.nextInt(64, 256) + 64;
    }
}
