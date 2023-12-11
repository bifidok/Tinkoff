package edu.hw9.task3;

import edu.hw9.task3.project2.Cell;
import edu.hw9.task3.project2.Maze;
import edu.hw9.task3.project2.solver.MazeSolver;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class DepthFirstSearchMultiThreadSolver implements MazeSolver {

    public Set<Cell> solve(Maze maze) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MazeSolveTask task = new MazeSolveTask(new HashSet<>(), maze, maze.getStart());
        forkJoinPool.execute(task);
        return task.join();
    }
}
