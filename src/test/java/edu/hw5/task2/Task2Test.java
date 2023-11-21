package edu.hw5.task2;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class Task2Test {

    @Test
    @DisplayName("Пятницы 13-е 1925 года")
    public void getFridaysThirteen_shouldReturnTwoDates() {
        List<LocalDate> expected = List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );

        var actual = Task2.getFridaysThirteen(1925);

        assertThat(expected.containsAll(actual)).isTrue();
    }

    @Test
    @DisplayName("Невалидный год")
    public void getFridaysThirteen_shouldThrowException_whenInvalidYear() {
        Throwable throwable = catchThrowable(() -> {
            Task2.getFridaysThirteen(-1);
        });

        assertThat(throwable).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid year");
    }

    @Test
    @DisplayName("Следующая пятница 13")
    public void getNextFridayThirteen_shouldNextFridayThirteenFromNow() {
        var today = LocalDate.of(2023, 11, 4);
        var nextFridayThirteen = LocalDate.of(2024, 9, 13);

        var actual = Task2.getNextFridayThirteen(today);

        assertThat(actual).isEqualTo(nextFridayThirteen);
    }
}
