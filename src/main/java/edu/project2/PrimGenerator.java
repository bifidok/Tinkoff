package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimGenerator implements MazeGenerator {
    private final int[][] waysToGo = new int[][] {
        {-1, 0},
        {0, 1},
        {1, 0},
        {0, -1},
    };

    public Maze generate(int width, int height) {
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

    private void fillMaze(Cell[][] grid, Cell startCell) {
        List<Cell> passages = new ArrayList<>();
        passages.add(startCell);
        Random random = new Random();
        do {
            Cell curCell = passages.get(random.nextInt(0, passages.size()));
            grid[curCell.y()][curCell.x()] = new Cell(curCell.x(), curCell.y(), CellType.EMPTY);
            List<Cell> walls = getWallsAround(grid, curCell);
            while (walls.size() > 1) {
                Cell randWall = walls.get(random.nextInt(0, walls.size()));
                int offsetX = randWall.x() - curCell.x();
                int offsetY = randWall.y() - curCell.y();
                if (isInBounds(grid, randWall.x() + offsetX, randWall.y() + offsetY)
                    && isWall(grid, randWall.x() + offsetX, randWall.y() + offsetY)) {
                    Cell newWall = new Cell(randWall.x(), randWall.y(), CellType.EMPTY);
                    Cell newPassage = new Cell(randWall.x() + offsetX, randWall.y() + offsetY, CellType.EMPTY);
                    grid[randWall.y()][randWall.x()] = newWall;
                    grid[newPassage.y()][newPassage.x()] = newPassage;
                    passages.add(newPassage);
                }
                walls.remove(randWall);
            }
            passages.remove(curCell);
        } while (passages.size() > 0);
    }

    private List<Cell> getWallsAround(Cell[][] grid, Cell cell) {
        List<Cell> walls = new ArrayList<>();
        for (int i = 0; i < waysToGo.length; i++) {
            int wallX = cell.x() + waysToGo[i][0];
            int wallY = cell.y() + waysToGo[i][1];
            if (isInBounds(grid, wallX, wallY) && isWall(grid, wallX, wallY)
                && isWall(grid, wallX, wallY)) {
                walls.add(grid[wallY][wallX]);
            }
        }
        return walls;
    }

    private Cell generateIn(Cell[][] grid) {
        Cell start = grid[1][1];
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][1].type().equals(CellType.EMPTY)) {
                start = new Cell(0, i, CellType.EMPTY);
                grid[i][0] = start;
                break;
            }
        }
        return start;
    }

    private Cell generateOut(Cell[][] grid) {
        Cell end = grid[grid.length - 1][grid[0].length - 1];
        for (int i = grid.length - 1; i >= 0; i--) {
            if (grid[i][grid[i].length - 2].type().equals(CellType.EMPTY)) {
                end = new Cell(grid[i].length - 2, i, CellType.EMPTY);
                grid[i][grid[i].length - 1] = end;
                break;
            }
        }
        return end;
    }

    private boolean isInBounds(Cell[][] grid, int x, int y) {
        return x > 0 && y > 0 && x < grid[0].length && y < grid.length;
    }

    private boolean isWall(Cell[][] grid, int x, int y) {
        return grid[y][x].type() == CellType.WALL;
    }
}
