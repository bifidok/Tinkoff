package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class BackwardIteratorTest {
    @Test
    @DisplayName("Базовый тест")
    public void backWardIterator_ShouldIterateBack() {
        List<String> list = List.of("1", "2", "3", "4", "5");
        int i = list.size() - 1;

        Iterator iterator = new BackwardIterator(list);

        while (iterator.hasNext()) {
            assertThat(iterator.next()).isEqualTo(list.get(i));
            i--;
        }
    }

    @Test
    @DisplayName("Пустой список в итератор")
    public void backWardIterator_ShouldNotHaveNext_whenEmptyList() {
        List<String> list = List.of();

        Iterator iterator = new BackwardIterator(list);
        Throwable throwable = catchThrowable(() -> {
            iterator.next();
        });

        assertThat(throwable).isInstanceOf(NoSuchElementException.class)
            .hasMessage("Theres no next element");
        assertThat(iterator.hasNext()).isFalse();
    }
}
