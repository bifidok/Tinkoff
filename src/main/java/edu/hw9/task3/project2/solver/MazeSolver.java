package edu.hw9.task3.project2.solver;

import edu.hw9.task3.project2.Cell;
import edu.hw9.task3.project2.Maze;
import java.util.Set;

public interface MazeSolver {
    Set<Cell> solve(Maze maze);
}
