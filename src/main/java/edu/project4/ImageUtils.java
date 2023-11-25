package edu.project4;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public final class ImageUtils {
    private final static int RED_BYTES_SHIFT = 16;
    private final static int GREEN_BYTES_SHIFT = 8;

    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) throws IOException {
        BufferedImage bufferedImage = convertFractalToBuffered(image);
        String fileFormat;
        switch (format) {
            case ImageFormat.BMP -> fileFormat = "BMP";
            case ImageFormat.JPEG -> fileFormat = "JPEG";
            default -> fileFormat = "PNG";
        }
        ImageIO.write(bufferedImage, fileFormat, filename.toFile());
    }

    public static void print(FractalImage image) {
        BufferedImage bufferedImage = convertFractalToBuffered(image);
        JLabel screen = new JLabel(new ImageIcon(bufferedImage));
        JPanel panel = new JPanel();
        panel.add(screen);
        JFrame window = new JFrame();
        window.setSize(image.width(), image.height());
        window.add(panel);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static BufferedImage convertFractalToBuffered(FractalImage image) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        var outputPixels = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
        var inputPixels = image.pixels();
        int pixelIndex = 0;
        for (int row = 0; row < bufferedImage.getHeight(); row++) {
            for (int col = 0; col < bufferedImage.getWidth(); col++) {
                int pixel = inputPixels[row][col].r() << RED_BYTES_SHIFT
                    | inputPixels[row][col].g() << GREEN_BYTES_SHIFT
                    | inputPixels[row][col].b();
                outputPixels[pixelIndex++] = pixel;
            }
        }
        return bufferedImage;
    }
}
