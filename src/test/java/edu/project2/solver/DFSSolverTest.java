package edu.project2.solver;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.generator.HuntAndKillGenerator;
import edu.project2.generator.MazeGenerator;
import edu.project2.generator.PrimGenerator;
import edu.project2.generator.RecursiveBacktrackGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DFSSolverTest {
    @Test
    @DisplayName("DFS solve recursive backtrack maze")
    public void solve_shouldSolveIdealMaze_whenMazeGeneratedWithRecursiveBacktracker() {
        MazeGenerator generator = new RecursiveBacktrackGenerator();
        Maze maze = generator.generate(10, 10);
        MazeSolver solver = new DepthFirstSearchSolver();

        List<Cell> answer = solver.solve(maze);
        Cell start = maze.getStart();
        Cell end = maze.getEnd();

        assertThat(answer.get(answer.size() - 1)).isEqualTo(end);
        assertThat(answer.get(0)).isEqualTo(start);
    }

    @Test
    @DisplayName("DFS solve hunt and kill maze")
    public void solve_shouldSolveIdealMaze_whenMazeGeneratedWithHuntAndKill() {
        MazeGenerator generator = new HuntAndKillGenerator();
        Maze maze = generator.generate(10, 10);
        MazeSolver solver = new DepthFirstSearchSolver();

        List<Cell> answer = solver.solve(maze);
        Cell start = maze.getStart();
        Cell end = maze.getEnd();

        assertThat(answer.get(answer.size() - 1)).isEqualTo(end);
        assertThat(answer.get(0)).isEqualTo(start);
    }

    @Test
    @DisplayName("DFS solve prim maze")
    public void solve_shouldSolveIdealMaze_whenMazeGeneratedWithPrim() {
        MazeGenerator generator = new PrimGenerator();
        Maze maze = generator.generate(10, 10);
        MazeSolver solver = new DepthFirstSearchSolver();

        List<Cell> answer = solver.solve(maze);
        Cell start = maze.getStart();
        Cell end = maze.getEnd();

        assertThat(answer.get(answer.size() - 1)).isEqualTo(end);
        assertThat(answer.get(0)).isEqualTo(start);
    }
}
