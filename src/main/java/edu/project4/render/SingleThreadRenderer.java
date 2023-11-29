package edu.project4.render;

import edu.project4.AffineFactorContainer;
import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.Random;

public class SingleThreadRenderer implements Renderer {
    private final static Point POINT_MIN = new Point(-1.777, -1.0);
    private final static Point POINT_MAX = new Point(1.777, 1.0);
    private final static double RANGE_X = POINT_MAX.x() - POINT_MIN.x();
    private final static double RANGE_Y = POINT_MAX.y() - POINT_MIN.y();
    private final static int SKIP_ITERATIONS = 20;
    private final static int AFFINE_FACTORS = 5;

    private final Random random = new Random();

    @Override
    public FractalImage render(FractalFlameConfiguration configuration, List<Transformation> variations) {
        Transformation transformation = variations.get(random.nextInt(0, variations.size()));
        int xResolution = configuration.width();
        int yResolution = configuration.height();
        var pixels = createInitialPixels(xResolution, yResolution);
        var affineFactors = createFactors(AFFINE_FACTORS);

        for (int num = 0; num < configuration.samples(); num++) {
            Point next = randomPoint();
            for (int iter = -SKIP_ITERATIONS; iter < configuration.iterPerSample(); iter++) {
                int iterAffineFactors = random.nextInt(0, affineFactors.length);
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
                                pixels[y1][x1] = getNewColor(pixels[y1][x1], affineFactors[iterAffineFactors]);
                            }
                        }
                    }
                }
            }
        }
        return new FractalImage(pixels, xResolution, yResolution);
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

    private Pixel[][] createInitialPixels(int width, int height) {
        Pixel[][] pixels = new Pixel[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixels[row][col] = new Pixel(0, 0, 0, 0, 0);
            }
        }
        return pixels;
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

    private Point computeLinear(AffineFactorContainer factors, Point next) {
        double x = factors.a() * next.x() + factors.b() * next.y() + factors.e();
        double y = factors.c() * next.x() + factors.d() * next.y() + factors.f();
        return new Point(x, y);
    }

    private Point randomPoint() {
        double newX = random.nextDouble(POINT_MIN.x(), POINT_MAX.x());
        double newY = random.nextDouble(POINT_MIN.y(), POINT_MAX.y());
        return new Point(newX, newY);
    }

    private Point rotate(Point nonLinear, double theta) {
        double xRotated = nonLinear.x() * Math.cos(theta) - nonLinear.y() * Math.sin(theta);
        double yRotated = nonLinear.x() * Math.sin(theta) + nonLinear.y() * Math.cos(theta);
        return new Point(xRotated, yRotated);
    }

    private double randomFactor() {
        return random.nextDouble(-1, 1);
    }

    @SuppressWarnings("MagicNumber")
    private int randomColor() {
        return random.nextInt(64, 256) + 64;
    }
}
