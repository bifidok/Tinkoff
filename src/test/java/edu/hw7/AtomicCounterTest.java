package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AtomicCounterTest {
    @Test
    @DisplayName("Увеличение счетчика(1 поток)")
    public void increaseCounter_when1Thread() {
        int threadsCount = 1;
        int incrementCount = 10000;

        int counter = AtomicCounter.increaseCounter(threadsCount, incrementCount);

        assertThat(counter).isEqualTo(threadsCount * incrementCount);
    }

    @Test
    @DisplayName("Увеличение счетчика(5 потоков)")
    public void increaseCounter_when5Threads() {
        int threadsCount = 5;
        int incrementCount = 10000;

        int counter = AtomicCounter.increaseCounter(threadsCount, incrementCount);

        assertThat(counter).isEqualTo(threadsCount * incrementCount);
    }
}
