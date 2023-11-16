package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Однопоточный алгоритм")
    public void calculate_whenSingleThread(){
        Task4SingleThread task4SingleThread = new Task4SingleThread();
        int pointsCount = 10000;
        double maxMeasurementError = 1;

        double pi = task4SingleThread.calculate(pointsCount);

        assertThat(Math.abs(pi - Math.PI) < maxMeasurementError).isTrue();
    }

    @Test
    @DisplayName("Многопоточный алгоритм")
    public void calculate_whenMultiThread(){
        Task4MultiThread task4SingleThread = new Task4MultiThread(5);
        int pointsCount = 10000;
        double maxMeasurementError = 1;

        double pi = task4SingleThread.calculate(pointsCount);

        assertThat(Math.abs(pi - Math.PI) < maxMeasurementError).isTrue();
    }
}
