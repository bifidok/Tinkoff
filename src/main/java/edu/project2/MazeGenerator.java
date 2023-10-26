package edu.project2;

public class MazeGenerator {
    private static int recursionLimiter = 0;
    private static int[][] waysToGo = new int[][] {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    private MazeGenerator() {
    }

    public static Maze generate(int width, int height) {
        Cell[][] grid = new Cell[height][width];
        generateBox(grid);
        fillMaze(grid,grid[1][1]);
        return new Maze(width, height, grid);
    }

    private static void generateBox(Cell[][] grid) {
        int height = grid.length;
        int width = grid[height - 1].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(j, i, CellType.WALL);
            }
        }
    }

    private static void fillMaze(Cell[][] grid, Cell curCell) {
        if (!canBeCutted(grid, curCell.x(), curCell.y()) || recursionLimiter >= 5) {
            recursionLimiter = 0;
            return;
        }
        recursionLimiter++;
        grid[curCell.y()][curCell.x()] = new Cell(curCell.x(), curCell.y(), CellType.EMPTY);
        for (int i = 0; i < 4; i++) {
            int[] newWay = waysToGo[i];
            fillMaze(grid, grid[curCell.y() + newWay[1]][curCell.x() + newWay[0]]);
        }
    }

    private static boolean canBeCutted(Cell[][] grid, int x, int y) {
        if (x <= 0 || y <= 0 || x > grid[0].length - 1 || y > grid.length - 1
            || grid[y][x].type().equals(CellType.EMPTY)) {
            return false;
        }
        int emptyAround = 0;
        for (int[] way : waysToGo) {
            if (!isWall(grid, x + way[0], y + way[1])) {
                emptyAround++;
            }
        }
        return emptyAround < 2;
    }

    private static boolean isWall(Cell[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid[0].length || y >= grid.length) {
            return false;
        } else if (x == 0 || y == 0 || x == grid[0].length
            || y == grid.length || grid[y][x].type() == CellType.WALL) {
            return true;
        }
        return false;
    }
}
