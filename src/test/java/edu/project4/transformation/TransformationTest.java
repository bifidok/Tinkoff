package edu.project4.transformation;

import edu.project4.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TransformationTest {
    private final static Random random = new Random();
    @Test
    @DisplayName("Синусоидальная")
    public void apply_whenSinusoidal(){
        double x = random.nextDouble(0,1);
        double y = random.nextDouble(0,1);
        Transformation transformation = new SinusoidalTransformation();
        double expectedX = Math.sin(x);
        double expectedY = Math.sin(y);

        Point transformed = transformation.apply(new Point(x,y));

        assertThat(transformed.x()).isEqualTo(expectedX);
        assertThat(transformed.y()).isEqualTo(expectedY);
    }
    @Test
    @DisplayName("Диск")
    public void apply_whenDisk(){
        double x = random.nextDouble(0,1);
        double y = random.nextDouble(0,1);
        Transformation transformation = new DiskTransformation();
        double radius = Math.sqrt(x * x + y * y) * Math.PI;
        double theta = Math.atan2(y, x) / Math.PI;
        double expectedX = theta * Math.sin(radius);
        double expectedY = theta * Math.cos(radius);

        Point transformed = transformation.apply(new Point(x,y));

        assertThat(transformed.x()).isEqualTo(expectedX);
        assertThat(transformed.y()).isEqualTo(expectedY);
    }

    @Test
    @DisplayName("Сфера")
    public void apply_whenSpherical(){
        double x = random.nextDouble(0,1);
        double y = random.nextDouble(0,1);
        Transformation transformation = new SphericalTransformation();
        double radius = 1.0 / (x * x + y * y);
        double expectedX = radius * x;
        double expectedY = radius * y;

        Point transformed = transformation.apply(new Point(x,y));

        assertThat(transformed.x()).isEqualTo(expectedX);
        assertThat(transformed.y()).isEqualTo(expectedY);
    }

    @Test
    @DisplayName("Спираль")
    public void apply_whenSpiral(){
        double x = random.nextDouble(0,1);
        double y = random.nextDouble(0,1);
        Transformation transformation = new SpiralTransformation();
        double radius = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);
        double expectedX = (1.0 / radius) * (Math.cos(theta) + Math.sin(radius));
        double expectedY = (1.0 / radius) * (Math.sin(theta) - Math.cos(radius));

        Point transformed = transformation.apply(new Point(x,y));

        assertThat(transformed.x()).isEqualTo(expectedX);
        assertThat(transformed.y()).isEqualTo(expectedY);
    }

    @Test
    @DisplayName("Линейная")
    public void apply_whenLinear(){
        double x = random.nextDouble(0,1);
        double y = random.nextDouble(0,1);
        Transformation transformation = new LinearTransformation();

        Point transformed = transformation.apply(new Point(x,y));

        assertThat(transformed.x()).isEqualTo(x);
        assertThat(transformed.y()).isEqualTo(y);
    }
}
