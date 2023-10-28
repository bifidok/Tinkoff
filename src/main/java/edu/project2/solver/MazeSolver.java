package edu.project2.solver;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.Set;

public interface MazeSolver {
    Set<Cell> solve(Maze maze);
}
