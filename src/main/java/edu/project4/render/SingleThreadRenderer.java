package edu.project4.render;

import edu.project4.AffineFactorContainer;
import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.transformation.SinusoidalTransformation;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.Random;

public class SingleThreadRenderer implements Renderer {
    private static final Random random = new Random();
    private static final Point POINT_MIN = new Point(-1.777, -1.0);
    private static final Point POINT_MAX = new Point(1.777, 1.0);

    @Override
    public FractalImage render(FractalFlameConfiguration configuration, List<Transformation> variations) {
        Transformation transformation = variations.get(random.nextInt(0,variations.size()));
        double rangeX = POINT_MAX.x() - POINT_MIN.x();
        double rangeY = POINT_MAX.y() - POINT_MIN.y();
        int xResolution = configuration.width();
        int yResolution = configuration.height();
        var pixels = createInitialPixels(xResolution, yResolution);
        var affineFactors = createFactors(3);

        for (int num = 0; num < configuration.samples(); num++) {
            double newX = random.nextDouble(POINT_MIN.x(), POINT_MAX.x());
            double newY = random.nextDouble(POINT_MIN.y(), POINT_MAX.y());
            Point next = new Point(newX, newY);

            for (int iter = -20; iter < configuration.iterPerSample(); iter++) {
                int iterAffineFactors = random.nextInt(0, affineFactors.length);
                Point linear = computeLinear(affineFactors[iterAffineFactors], next);
                Point nonLinear = transformation.apply(linear);

                if (iter >= 0 && nonLinear.x() >= POINT_MIN.x() && nonLinear.x() <= POINT_MAX.x()
                    && nonLinear.y() >= POINT_MIN.y() && nonLinear.y() <= POINT_MAX.y()) {

                    double theta2 = 0.0;
                    for (int s = 0; s < configuration.symmetry(); s++) {
                        Point rotated = rotate(nonLinear, theta2);
                        if (rotated.x() >= POINT_MIN.x() && rotated.x() <= POINT_MAX.x()
                            && rotated.y() >= POINT_MIN.y() && rotated.y() <= POINT_MAX.y()) {

                            int x1 = xResolution - (int) (((POINT_MAX.x() - rotated.x()) / (rangeX)) * xResolution);
                            int y1 = yResolution - (int) (((POINT_MAX.y() - rotated.y()) / (rangeY)) * yResolution);
                            if (x1 < xResolution && y1 < yResolution) {
                                int r = affineFactors[iterAffineFactors].startRed();
                                int g = affineFactors[iterAffineFactors].startGreen();
                                int b = affineFactors[iterAffineFactors].startBlue();
                                if (pixels[y1][x1].hitCount() > 0) {
                                    r = (pixels[y1][x1].r() + r) / 2;
                                    g = (pixels[y1][x1].g() + g) / 2;
                                    b = (pixels[y1][x1].b() + b) / 2;
                                }
                                pixels[y1][x1] = new Pixel(r, g, b, pixels[y1][x1].hitCount() + 1, 0);
                            }
                        }
                        theta2 += (Math.PI * 2) / configuration.symmetry();
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
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                pixels[col][row] = new Pixel(0, 0, 0, 0, 0);
            }
        }
        return pixels;
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

    private double randomFactor() {
        return random.nextDouble(-1.5, 1.5);
    }

    private int randomColor() {
        return random.nextInt(64, 256) + 64;
    }
}
