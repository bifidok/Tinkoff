package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazePrinter {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String block = "\u001b[0m   \u001b[32m";
    private final static String empty = "   ";

    private MazePrinter() {
    }

    // a lot of strings
    public static void print(Maze maze) {
        var grid = maze.getGrid();
        for (int i = 0; i < maze.getHeight(); i++) {
            var line = render(grid[i]);
            LOGGER.info(line);
        }
    }

    private static String render(Cell[] row) {
        StringBuilder builder = new StringBuilder();
        for (Cell cell : row) {
            if (cell.type().equals(CellType.EMPTY)) {
                builder.append(empty);
            } else {
                builder.append(block);
            }
        }
        return builder.toString();
    }
}
