package edu.project2.solver;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.List;

public interface MazeSolver {
    List<Cell> solve(Maze maze);
}
