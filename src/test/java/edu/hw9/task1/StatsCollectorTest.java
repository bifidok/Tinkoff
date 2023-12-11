package edu.hw9.task1;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatsCollectorTest {
    @Test
    @DisplayName("Корректность рассчетов")
    @RepeatedTest(10)
    public void statsCollector_shouldReturnCorrectMetric() {
        StatsCollector collector = new StatsCollector();
        SecureRandom random = new SecureRandom();
        double[] data = new double[5];
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextDouble(1, 10);
        }
        Double min = Arrays.stream(data).min().orElse(0);
        Double max = Arrays.stream(data).max().orElse(0);
        Double average = Arrays.stream(data).average().orElse(0);
        Double sum = Arrays.stream(data).sum();

        collector.push("metric", data);
        var metric = collector.stats().stream()
            .findFirst()
            .get()
            .getValue();

        assertThat(min).isEqualTo(metric.min());
        assertThat(max).isEqualTo(metric.max());
        assertThat(average).isEqualTo(metric.average());
        assertThat(sum).isEqualTo(metric.sum());
    }

    @Test
    @DisplayName("Корректность рассчетов(потоки передают значения)")
    @RepeatedTest(10)
    public void statsCollector_shouldReturnCorrectMetric_whenMultiThread() {
        StatsCollector collector = new StatsCollector();
        SecureRandom random = new SecureRandom();
        ConcurrentHashMap<String, Metric> expectedMap = new ConcurrentHashMap<>();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                double[] data = new double[5];
                for (int j = 0; j < data.length; j++) {
                    data[j] = random.nextDouble(1, 10);
                }
                Double min = Arrays.stream(data).min().orElse(0);
                Double max = Arrays.stream(data).max().orElse(0);
                Double average = Arrays.stream(data).average().orElse(0);
                Double sum = Arrays.stream(data).sum();
                expectedMap.put(String.valueOf(finalI), new Metric(sum, average, max, min));
                collector.push(String.valueOf(finalI), data);
            });
            threadList.add(thread);
            thread.start();
        }
        for (var thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        var metricsActual = collector.stats();

        for (var entry : metricsActual) {
            assertThat(expectedMap.get(entry.getKey())).isNotNull();
            var metricExpected = expectedMap.get(entry.getKey());
            var metricActual = entry.getValue();
            assertThat(metricActual.average()).isEqualTo(metricExpected.average());
            assertThat(metricActual.min()).isEqualTo(metricExpected.min());
            assertThat(metricActual.max()).isEqualTo(metricExpected.max());
            assertThat(metricActual.sum()).isEqualTo(metricExpected.sum());
        }
    }

    @Test
    @DisplayName("Пустой массив")
    public void push_whenEmptyArray() {
        StatsCollector collector = new StatsCollector();
        double[] data = {};
        Double min = Arrays.stream(data).min().orElse(0);
        Double max = Arrays.stream(data).max().orElse(0);
        Double average = Arrays.stream(data).average().orElse(0);
        Double sum = Arrays.stream(data).sum();

        collector.push("metric", data);
        var metric = collector.stats().stream()
            .findFirst()
            .get()
            .getValue();

        assertThat(min).isEqualTo(metric.min());
        assertThat(max).isEqualTo(metric.max());
        assertThat(average).isEqualTo(metric.average());
        assertThat(sum).isEqualTo(metric.sum());
    }

    @Test
    @DisplayName("Null массив")
    public void push_whenNullArray() {
        StatsCollector collector = new StatsCollector();

        collector.push("metric", null);
        var metrics = collector.stats();

        assertThat(metrics.size()).isEqualTo(0);
    }
}
