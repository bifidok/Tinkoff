package edu.hw8.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FixedThreadPoolTest {
    @Test
    @DisplayName("Инкрементирование счетчика")
    public void execute_shouldCorrectlyIncrement() {
        int threadsCount = 30;
        int incrementOffset = 100000;
        ThreadPool threadPool = FixedThreadPool.create(threadsCount);
        MyCounter counter = new MyCounter(incrementOffset);

        threadPool.start();
        for (int i = 0; i < threadsCount; i++) {
            threadPool.execute(counter::increment);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            threadPool.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(counter.counter.get()).isEqualTo(threadsCount * incrementOffset);
    }

    public static class MyCounter {
        int incrementOffset;
        AtomicInteger counter = new AtomicInteger(0);

        public MyCounter(int incrementOffset) {
            this.incrementOffset = incrementOffset;
        }

        public void increment() {
            for (int i = 0; i < incrementOffset; i++) {
                counter.getAndIncrement();
            }
        }
    }
}
