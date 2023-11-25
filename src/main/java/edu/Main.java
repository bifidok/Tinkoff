package edu;

import edu.project4.AffineFactorContainer;
import edu.project4.ImageUtils;
import edu.project4.Pixel;
import edu.project4.processor.GammaCorrection;
import edu.project4.processor.ImageProcessor;
import edu.project4.render.FractalFlameConfiguration;
import edu.project4.render.Renderer;
import edu.project4.render.SingleThreadRenderer;
import edu.project4.transformation.DiskTransformation;
import edu.project4.transformation.SinusoidalTransformation;
import edu.project4.transformation.SphericalTransformation;
import edu.project4.transformation.SpiralTransformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();
    static Random random = new Random();

    private Main() {
    }

    public static void main(String[] args) throws IOException {
        Renderer renderer = new SingleThreadRenderer();
        var image = renderer.render(
            new FractalFlameConfiguration(1980,1020,20000,1000,2),
            List.of(new SinusoidalTransformation()));
        ImageProcessor processor = new GammaCorrection();
        processor.process(image);
        ImageUtils.print(image);

    }


}
