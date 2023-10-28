package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashSet;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        MazeGenerator generator = new HuntAndKillGenerator();
        MazeGenerator generator1 = new PrimGenerator();
        MazeGenerator generator2 = new RecursiveBacktrackGenerator();
        Maze maze = generator.generate(30, 15);
        Maze maze1 = generator1.generate(30, 15);
        Maze maze2 = generator2.generate(30, 15);
        BreadthFirstSearchSolver solver = new BreadthFirstSearchSolver();
        MazePrinter printer = new MazePrinter();
        var ans = solver.solve(maze);
        var ans1 = solver.solve(maze1);
        var ans2 = solver.solve(maze2);
        printer.print(maze,ans);
        printer.print(maze1,ans1);
        printer.print(maze2,ans2);
    }
}
