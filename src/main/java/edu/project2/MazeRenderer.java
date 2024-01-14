package edu.project2;

import java.util.Set;

public class MazeRenderer implements Renderer {
    private final static String ANSWER_BLOCK = "\u001b[0m * \u001b[32m";
    private final static String PASSAGE = "\u001b[0m   \u001b[32m";
    private final static String WALL = "   ";

    @Override
    public String render(Maze maze, Set<Cell> path) {
        StringBuilder builder = new StringBuilder(maze.getWidth() * maze.getHeight());
        builder.append("\n");
        var grid = maze.getGrid();
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.type().equals(CellType.EMPTY)) {
                    if (path.contains(cell)) {
                        builder.append(ANSWER_BLOCK);
                    } else {
                        builder.append(PASSAGE);
                    }
                } else {
                    builder.append(WALL);
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
