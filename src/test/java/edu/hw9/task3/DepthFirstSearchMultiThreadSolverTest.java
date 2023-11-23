package edu.hw9.task3;

import edu.hw9.task3.project2.Maze;
import edu.hw9.task3.project2.generator.MazeGenerator;
import edu.hw9.task3.project2.generator.RecursiveBacktrackGenerator;
import edu.hw9.task3.project2.solver.MazeSolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class DepthFirstSearchMultiThreadSolverTest {
    @Test
    @DisplayName("DFS решение")
    @RepeatedTest(10)
    public void solve_shouldSolveIdealMaze_whenMazeGeneratedWithRecursiveBacktracker() {
        MazeGenerator generator = new RecursiveBacktrackGenerator();
        Maze maze = generator.generate(30, 30);
        MazeSolver solver = new DepthFirstSearchMultiThreadSolver();

        var answer = solver.solve(maze);

        assertThat(answer.contains(maze.getStart())).isTrue();
        assertThat(answer.contains(maze.getEnd())).isTrue();
    }
}
