package edu.project2.solver;

import edu.project2.Maze;
import edu.project2.generator.HuntAndKillGenerator;
import edu.project2.generator.MazeGenerator;
import edu.project2.generator.PrimGenerator;
import edu.project2.generator.RecursiveBacktrackGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DFSSolverTest {
    @Test
    @DisplayName("DFS solve recursive backtrack maze")
    public void solve_shouldSolveIdealMaze_whenMazeGeneratedWithRecursiveBacktracker() {
        MazeGenerator generator = new RecursiveBacktrackGenerator();
        Maze maze = generator.generate(10, 10);
        MazeSolver solver = new DepthFirstSearchSolver();

        var answer = solver.solve(maze);

        assertThat(answer.contains(maze.getStart())).isTrue();
        assertThat(answer.contains(maze.getEnd())).isTrue();
    }

    @Test
    @DisplayName("DFS solve hunt and kill maze")
    public void solve_shouldSolveIdealMaze_whenMazeGeneratedWithHuntAndKill() {
        MazeGenerator generator = new HuntAndKillGenerator();
        Maze maze = generator.generate(10, 10);
        MazeSolver solver = new DepthFirstSearchSolver();

        var answer = solver.solve(maze);

        assertThat(answer.contains(maze.getStart())).isTrue();
        assertThat(answer.contains(maze.getEnd())).isTrue();
    }

    @Test
    @DisplayName("DFS solve prim maze")
    public void solve_shouldSolveIdealMaze_whenMazeGeneratedWithPrim() {
        MazeGenerator generator = new PrimGenerator();
        Maze maze = generator.generate(10, 10);
        MazeSolver solver = new DepthFirstSearchSolver();

        var answer = solver.solve(maze);

        assertThat(answer.contains(maze.getStart())).isTrue();
        assertThat(answer.contains(maze.getEnd())).isTrue();
    }
}
