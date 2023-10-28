package edu.project2.generator;

import edu.project2.Cell;
import edu.project2.CellType;
import edu.project2.Maze;
import java.util.Random;

public class HuntAndKillGenerator implements MazeGenerator {
    private final static int INCORRECT_SIDE_LENGTH = 3;

    private final int[][] waysToGo = new int[][] {
        {-1, 0},
        {0, 1},
        {1, 0},
        {0, -1},
    };

    public Maze generate(int width, int height) {
        if (width < INCORRECT_SIDE_LENGTH || height < INCORRECT_SIDE_LENGTH) {
            throw new IllegalArgumentException("Maze cant be such small");
        }
        Cell[][] grid = new Cell[height][width];
        generateBox(grid);
        fillMaze(grid, new Cell(1, 1, CellType.EMPTY));
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

    private void fillMaze(Cell[][] grid, Cell start) {
        grid[start.y()][start.x()] = new Cell(start.x(), start.y(), CellType.EMPTY);
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[i].length - 1; j++) {
                hunt(grid, grid[i][j]);
            }
        }
    }

    private void hunt(Cell[][] grid, Cell curCell) {
        if (!canMakePassage(grid, curCell.x(), curCell.y())) {
            return;
        }
        int rand = new Random().nextInt(0, waysToGo.length);
        int[] way = waysToGo[rand];
        grid[curCell.y()][curCell.x()] = new Cell(curCell.x(), curCell.y(), CellType.EMPTY);
        Cell newCell = grid[curCell.y() + way[1]][curCell.x() + way[0]];
        hunt(grid, newCell);
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
        return start;
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
        return end;
    }

    private boolean canMakePassage(Cell[][] grid, int x, int y) {
        if (x <= 0 || y <= 0 || x >= grid[0].length - 1 || y >= grid.length - 1) {
            return false;
        }
        int emptyAround = 0;
        for (int[] way : waysToGo) {
            if (!isWall(grid, x + way[0], y + way[1])) {
                emptyAround++;
            }
        }
        return emptyAround == 1;
    }

    private boolean isWall(Cell[][] grid, int x, int y) {
        return x <= 0 || y <= 0 || x >= grid[0].length - 1
            || y >= grid.length - 1 || grid[y][x].type() == CellType.WALL;
    }
}
