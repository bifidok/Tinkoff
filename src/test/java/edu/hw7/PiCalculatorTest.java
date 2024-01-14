package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PiCalculatorTest {
    @Test
    @DisplayName("Однопоточный алгоритм")
    public void calculate_whenSingleThread() {
        PiCalculatorSingleThread task4SingleThread = new PiCalculatorSingleThread();
        int pointsCount = 10000;
        double maxMeasurementError = 1;

        double pi = task4SingleThread.calculate(pointsCount);

        assertThat(Math.abs(pi - Math.PI) < maxMeasurementError).isTrue();
    }

    @Test
    @DisplayName("Многопоточный алгоритм")
    public void calculate_whenMultiThread() {
        PiCalculatorMultiThread task4SingleThread = new PiCalculatorMultiThread(5);
        int pointsCount = 10000;
        double maxMeasurementError = 1;

        double pi = task4SingleThread.calculate(pointsCount);

        assertThat(Math.abs(pi - Math.PI) < maxMeasurementError).isTrue();
    }
}
