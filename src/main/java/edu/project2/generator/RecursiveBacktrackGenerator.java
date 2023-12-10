package edu.project2.generator;

import edu.project2.Cell;
import edu.project2.CellType;
import edu.project2.Maze;

public class RecursiveBacktrackGenerator implements MazeGenerator {
    private final static int STRAIGHT_PASSAGE_LIMIT = 4;
    private final static int MIN_SIDE_LENGTH = 3;
    private final static int MAX_SIDE_LENGTH = 100;

    private final int[][] waysToGo = new int[][] {
        {-1, 0},
        {0, 1},
        {1, 0},
        {0, -1},
    };

    private int pathGeneratingCounter;

    public Maze generate(int width, int height) {
        if (width < MIN_SIDE_LENGTH || height < MIN_SIDE_LENGTH) {
            throw new IllegalArgumentException("Maze cant be such small");
        } else if (width > MAX_SIDE_LENGTH || height > MAX_SIDE_LENGTH) {
            throw new IllegalArgumentException("No memory for recursion");
        }
        Cell[][] grid = new Cell[height][width];
        generateBox(grid);
        fillMaze(grid, grid[height / 2][width / 2]);
        Cell start = generateIn(grid);
        Cell end = generateOut(grid);
        return new Maze(width, height, grid, start, end);
    }

    private void generateBox(Cell[][] grid) {
        int height = grid.length;
        int width = grid[height - 1].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(j, i, CellType.WALL);
            }
        }
    }

    private void fillMaze(Cell[][] grid, Cell curCell) {
        if (!canMakePassage(grid, curCell.x(), curCell.y()) || pathGeneratingCounter >= STRAIGHT_PASSAGE_LIMIT) {
            pathGeneratingCounter = 0;
            return;
        }
        pathGeneratingCounter++;
        grid[curCell.y()][curCell.x()] = new Cell(curCell.x(), curCell.y(), CellType.EMPTY);
        for (int i = 0; i < waysToGo.length; i++) {
            int[] newWay = waysToGo[i];
            fillMaze(grid, grid[curCell.y() + newWay[1]][curCell.x() + newWay[0]]);
        }
    }

    private Cell generateIn(Cell[][] grid) {
        Cell start = grid[1][1];
        for (int i = 1; i < grid.length - 1; i++) {
            if (grid[i][1].type().equals(CellType.EMPTY)) {
                start = grid[i][0];
                break;
            }
        }
        grid[start.y()][start.x()] = new Cell(start.x(), start.y(), CellType.EMPTY);
        return grid[start.y()][start.x()];
    }

    private Cell generateOut(Cell[][] grid) {
        Cell end = grid[grid.length - 2][grid[0].length - 1];
        for (int i = grid.length - 2; i > 0; i--) {
            if (grid[i][grid[i].length - 2].type().equals(CellType.EMPTY)) {
                end = grid[i][grid[i].length - 1];
                break;
            }
        }
        grid[end.y()][end.x()] = new Cell(end.x(), end.y(), CellType.EMPTY);
        return grid[end.y()][end.x()];
    }

    private boolean canMakePassage(Cell[][] grid, int x, int y) {
        if (x <= 0 || y <= 0 || x >= grid[0].length - 1 || y >= grid.length - 1
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

    private boolean isWall(Cell[][] grid, int x, int y) {
        return x <= 0 || y <= 0 || x >= grid[0].length - 1
            || y >= grid.length - 1 || grid[y][x].type() == CellType.WALL;
    }
}
