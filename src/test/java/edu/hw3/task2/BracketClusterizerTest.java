package edu.hw3.task2;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class BracketClusterizerTest {
    @Test
    @DisplayName("Базовый тест")
    public void clusterize_shouldCorrectlyClusterize() {
        String brackets = "((()))(())()()(()())";
        var answer = List.of("((()))", "(())", "()", "()", "(()())");

        var result = BracketClusterizer.clusterize(brackets);

        assertThat(result.containsAll(answer)).isTrue();
    }

    @Test
    @DisplayName("Пустая строка")
    public void clusterize_shouldReturnEmptyList() {
        String brackets = "";

        var result = BracketClusterizer.clusterize(brackets);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Невалидная строка")
    public void clusterize_shouldThrowException() {
        String brackets = "((())()))(()(()()))";

        Throwable throwable = catchThrowable(() -> {
            BracketClusterizer.clusterize(brackets);
        });

        assertThat(throwable).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Unbalanced brackets");
    }
}
