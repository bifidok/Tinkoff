package edu.project2;

import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazePrinter {
    private final static Logger LOGGER = LogManager.getLogger();

    private final MazeRenderer renderer;

    public MazePrinter() {
        renderer = new MazeRenderer();
    }

    public void print(Maze maze, Set<Cell> path) {
        LOGGER.info("");
        String output = renderer.render(maze, path);
        LOGGER.info(output);
        LOGGER.info("");
    }
}
