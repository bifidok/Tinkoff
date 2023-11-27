package edu;

import edu.project4.ImageFormat;
import edu.project4.ImageUtils;
import edu.project4.processor.GammaCorrection;
import edu.project4.processor.ImageProcessor;
import edu.project4.render.FractalFlameConfiguration;
import edu.project4.render.MultiThreadRenderer;
import edu.project4.render.Renderer;
import edu.project4.render.SingleThreadRenderer;
import edu.project4.transformation.DiskTransformation;
import edu.project4.transformation.SinusoidalTransformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();
    static Random random = new Random();

    private Main() {
    }

    public static void main(String[] args) throws IOException {
//        long time3 = System.nanoTime();
//        Renderer renderer1 = new SingleThreadRenderer();
//        var image1 = renderer1.render(
//            new FractalFlameConfiguration(1920, 1080, 20000, 1000, 1),
//            List.of(new SinusoidalTransformation())
//        );
//        ImageProcessor processor1 = new GammaCorrection(2.2);
//        processor1.process(image1);
//        ImageUtils.print(image1);
//        long time4 = System.nanoTime() - time3;
//        System.out.println("single-threaded time: " + ((time4) / 1000000) + " milisec");
        for (int i = 2; i < 6; i++) {
            long time1 = System.nanoTime();
            Renderer renderer = new MultiThreadRenderer(i);
            var image = renderer.render(
                new FractalFlameConfiguration(1920, 1080, 20000, 1000, 1),
                List.of(new SinusoidalTransformation())
            );
            ImageProcessor processor = new GammaCorrection(2.2);
            processor.process(image);
            ImageUtils.print(image);
            long time2 = System.nanoTime() - time1;
            System.out.println("multi-threaded time " + i + " threads: " + ((time2) / 1000000) + " milisec");
        }
    }
}
