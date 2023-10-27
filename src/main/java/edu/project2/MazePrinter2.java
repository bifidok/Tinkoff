package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazePrinter2 {
    private final static Logger LOGGER = LogManager.getLogger();
    private final String answerBlock = "\u001b[0m * \u001b[32m";
    private final String passage = "\u001b[0m   \u001b[32m";
    private final String wall = "   ";

    public void print(Maze maze) {
        StringBuilder builder = new StringBuilder(maze.getWidth());
        LOGGER.info("");
        var grid = maze.getGrid();
        for (int i = 0; i < maze.getHeight(); i++) {
            var row = render(grid[i], builder);
            LOGGER.info(row);
        }
        LOGGER.info("");
    }

    private String render(Cell[] row, StringBuilder builder) {
        builder.delete(0, builder.length());
        for (Cell cell : row) {
            if (cell.type().equals(CellType.EMPTY)) {
                builder.append(passage);
            } else {
                builder.append(wall);
            }
        }
        return builder.toString();
    }
}
