package edu;

import edu.project4.Afin;
import edu.project4.Pixel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.Random;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();
    static Random random = new Random();

    private Main() {
    }

    public static void main(String[] args) throws IOException {
        int xRes = 1920;
        int yRes = 1080;
        int symmetry = 2;
        Pixel[] pixels = new Pixel[xRes * yRes];
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = new Pixel(0, 0, 0, 0, 0);
        }
        double XMIN = -1.777, XMAX = 1.777, YMIN = -1, YMAX = 1;
        Afin[] afins = {
            new Afin(random(),
                random(),
                random(),
                random(),
                random(),
                random(), 134, 111, 166 // фиолетовый
            ),
            new Afin(random(),
                random(),
                random(),
                random(),
                random(),
                random(), 13, 111, 75 // темно-зеленый
            ),
            new Afin(random(),
                random(),
                random(),
                random(),
                random(),
                random(), 249, 70, 238 // розовый
            ),
            new Afin(random(),
                random(),
                random(),
                random(),
                random(),
                random(), 179, 235, 38 // желтый
            ),
            new Afin(random(),
                random(),
                random(),
                random(),
                random(),
                random(), 55, 237, 144 // светло-зеленый
            )
        };
        for (int j = 0; j < 20000; j++) {
            double newX = random.nextDouble(XMIN, XMAX);
            double newY = random.nextDouble(YMIN, YMAX);

            for (int i = -20; i < 1000; i++) {
                int eq = random.nextInt(0, afins.length);
                double x = afins[eq].a() * newX + afins[eq].b() * newY + afins[eq].e();
                double y = afins[eq].c() * newX + afins[eq].d() * newY + afins[eq].f();
//                newX = x; //linear
//                newY = y;
//                newX = Math.sin(x);  //sinusoidal
//                newY = Math.sin(y);
//                double rad = 1.0 / (x * x + y * y); // spherical
//                newX = rad * x;
//                newY = rad * y;
//                double rad = Math.sqrt(x * x + y * y); // spiral
//                double theta = Math.atan2(y,x);
//                newX = (1.0/rad)*(Math.cos(theta) + Math.sin(rad));
//                newY = (1.0/rad)*(Math.sin(theta) - Math.cos(rad));//
                double rad = Math.sqrt(x * x + y * y) * Math.PI; // disk
                double theta = Math.atan2(y,x) / Math.PI;
                newX = theta * Math.sin(rad);
                newY = theta * Math.cos(rad);
                if (i >= 0) {
                    double theta2 = 0.0;
                    for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                        double x_rot = newX * Math.cos(theta2) - newY * Math.sin(theta2);
                        double y_rot = newX * Math.sin(theta2) + newY * Math.cos(theta2);
                        if (x_rot >= XMIN && x_rot <= XMAX && y_rot >= YMIN && y_rot <= YMAX) {
                            int x1 = xRes - (int) (((XMAX - newX) / (XMAX - XMIN)) * xRes);
                            int y1 = yRes - (int) (((YMAX - newY) / (YMAX - YMIN)) * yRes);
                            if (x1 >= 0 && y1 >= 0 && x1 < xRes && y1 < yRes) {
                                double r;
                                double g;
                                double b;
                                if (pixels[x1 * y1].hitCount() == 0) {
                                    r = afins[eq].red();
                                    g = afins[eq].green();
                                    b = afins[eq].blue();
                                } else {
                                    r = (pixels[x1 * y1].r() + afins[eq].red()) / 2;
                                    g = (pixels[x1 * y1].g() + afins[eq].green()) / 2;
                                    b = (pixels[x1 * y1].b() + afins[eq].blue()) / 2;
                                }
                                pixels[x1 * y1] = new Pixel(r, g, b, pixels[x1 * y1].hitCount() + 1, 0);
                            }
                        }
                    }
                }
            }
        }

        double max = 0.0;
        double gamma = 10.9;
        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                if (pixels[row * col].hitCount() != 0) {
                    Pixel pixel = pixels[row * col];
                    double normal = log10(pixel.hitCount());
                    pixels[row * col] = new Pixel(pixel.r(), pixel.g(), pixel.b(), pixel.hitCount(), normal);
                    if (normal > max) {
                        max = normal;
                    }
                }
            }
        }
        for (int row = 0; row < xRes; row++) {
            for (int col = 0; col < yRes; col++) {
                Pixel pixel = pixels[row * col];
                double normal = pixel.normal() / max;
                double r = (pixel.r() * pow(pixel.normal(), (1.0 / gamma)));
                double g = (pixel.g() * pow(pixel.normal(), (1.0 / gamma)));
                double b = (pixel.b() * pow(pixel.normal(), (1.0 / gamma)));
                pixels[row * col] = new Pixel(r, g, b, pixel.hitCount(), normal);
            }
        }

        BufferedImage image = new BufferedImage(xRes, yRes, BufferedImage.TYPE_INT_RGB);
        var pixels2 = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        int pixelIndex = 0;
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                pixels2[pixelIndex++] =
                    (int) pixels[i * j].r() << 16 | (int) pixels[i * j].g() << 8 |
                        (int) pixels[i * j].b(); // red | green | blue
            }
        }
        JLabel screen = new JLabel(new ImageIcon(image));
        JPanel panel = new JPanel();
        panel.add(screen);
        JFrame window = new JFrame();
        window.setSize(image.getWidth() + 50, image.getHeight() + 50);
        window.add(panel);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    static double random() {
        return random.nextDouble(-1.5, 1.5);
    }
}
