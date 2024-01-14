package edu.project2.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class PrimTest {

    @Test
    @DisplayName("Базовый тест")
    public void generate_shouldGenerateIdealMaze() {
        MazeGenerator generator = new PrimGenerator();

        var maze = generator.generate(10, 10);

        assertThat(maze.getWidth()).isEqualTo(10);
        assertThat(maze.getHeight()).isEqualTo(10);
        assertThat(maze.getStart()).isNotNull();
        assertThat(maze.getEnd()).isNotNull();
    }

    @Test
    @DisplayName("Некорректный лабиринт")
    public void generate_shouldThrowException_whenMazeIncorrect() {
        MazeGenerator generator = new PrimGenerator();

        Throwable thrown = catchThrowable(() -> {
            var maze = generator.generate(1, 1);
        });

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Maze cant be such small");
    }
}
