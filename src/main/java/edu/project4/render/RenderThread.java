package edu.project4.render;

import edu.project4.AffineFactorContainer;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.transformation.Transformation;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class RenderThread implements Runnable {

    private final static Point POINT_MIN = new Point(-1.777, -1.0);
    private final static Point POINT_MAX = new Point(1.777, 1.0);
    private final static double RANGE_X = POINT_MAX.x() - POINT_MIN.x();
    private final static double RANGE_Y = POINT_MAX.y() - POINT_MIN.y();
    private final static Random RANDOM = new Random();
    private final static int SKIP_ITERATIONS = 20;

    private final FractalFlameConfiguration configuration;
    private final AffineFactorContainer[] affineFactors;
    private final Transformation transformation;
    private final ConcurrentPixelTwoDimensionalArray pixels;
    private AtomicInteger sample;

    public RenderThread(
        FractalFlameConfiguration configuration,
        AffineFactorContainer[] factorContainers,
        Transformation transformation,
        ConcurrentPixelTwoDimensionalArray pixels,
        AtomicInteger sample
    ) {
        this.configuration = configuration;
        this.affineFactors = factorContainers;
        this.transformation = transformation;
        this.pixels = pixels;
        this.sample = sample;
    }

    @Override
    public void run() {
        int xResolution = configuration.width();
        int yResolution = configuration.height();
        for (; sample.get() < configuration.samples(); sample.incrementAndGet()) {
            Point next = randomPoint();

            for (int iter = -SKIP_ITERATIONS; iter < configuration.iterPerSample(); iter++) {
                int iterAffineFactors = RANDOM.nextInt(0, affineFactors.length);
                Point linear = computeLinear(affineFactors[iterAffineFactors], next);
                Point nonLinear = transformation.apply(linear);
                next = nonLinear;

                if (iter > 0) {
                    double theta2 = 0.0;
                    for (int s = 0; s < configuration.symmetry(); s++) {
                        theta2 += (Math.PI * 2) / configuration.symmetry();
                        Point rotated = rotate(nonLinear, theta2);
                        if (rotated.x() >= POINT_MIN.x() && rotated.x() <= POINT_MAX.x()
                            && rotated.y() >= POINT_MIN.y() && rotated.y() <= POINT_MAX.y()) {
                            int x1 = xResolution - (int) (((POINT_MAX.x() - rotated.x()) / (RANGE_X)) * xResolution);
                            int y1 = yResolution - (int) (((POINT_MAX.y() - rotated.y()) / (RANGE_Y)) * yResolution);
                            if (x1 < xResolution && y1 < yResolution) {
                                Pixel updatedPixel = getNewColor(pixels.get(y1, x1), affineFactors[iterAffineFactors]);
                                pixels.set(y1, x1, updatedPixel);
                            }
                        }
                    }
                }
            }
        }
    }

    private Pixel getNewColor(Pixel pixel, AffineFactorContainer factors) {
        int r;
        int g;
        int b;
        if (pixel.hitCount() == 0) {
            r = factors.startRed();
            g = factors.startGreen();
            b = factors.startBlue();
        } else {
            r = (pixel.r() + factors.startRed()) / 2;
            g = (pixel.g() + factors.startGreen()) / 2;
            b = (pixel.b() + factors.startBlue()) / 2;
        }
        return new Pixel(r, g, b, pixel.hitCount() + 1, 0);
    }

    private Point randomPoint() {
        double newX = RANDOM.nextDouble(POINT_MIN.x(), POINT_MAX.x());
        double newY = RANDOM.nextDouble(POINT_MIN.y(), POINT_MAX.y());
        return new Point(newX, newY);
    }

    private Point computeLinear(AffineFactorContainer factors, Point next) {
        double x = factors.a() * next.x() + factors.b() * next.y() + factors.e();
        double y = factors.c() * next.x() + factors.d() * next.y() + factors.f();
        return new Point(x, y);
    }

    private Point rotate(Point nonLinear, double theta) {
        double xRotated = nonLinear.x() * Math.cos(theta) - nonLinear.y() * Math.sin(theta);
        double yRotated = nonLinear.x() * Math.sin(theta) + nonLinear.y() * Math.cos(theta);
        return new Point(xRotated, yRotated);
    }
}
