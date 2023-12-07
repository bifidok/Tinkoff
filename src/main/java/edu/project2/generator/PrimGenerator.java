package edu.project2.generator;

import edu.project2.Cell;
import edu.project2.CellType;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimGenerator implements MazeGenerator {
    private final static int MIN_SIDE_LENGTH = 3;

    private final int[][] waysToGo = new int[][] {
        {-1, 0},
        {0, 1},
        {1, 0},
        {0, -1},
    };

    public Maze generate(int width, int height) {
        if (width < MIN_SIDE_LENGTH || height < MIN_SIDE_LENGTH) {
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
        Cell end = grid[grid.length / 2][grid[0].length - 1];
        int outDrillCounter = 0;
        while (isWall(grid, end.x() - outDrillCounter, end.y())) {
            grid[end.y()][end.x() - outDrillCounter] = new Cell(end.x() - outDrillCounter, end.y(), CellType.EMPTY);
            outDrillCounter++;
        }
        return grid[grid.length / 2][grid[0].length - 1];
    }

    private boolean isInBounds(Cell[][] grid, int x, int y) {
        return x > 0 && y > 0 && x < grid[0].length - 1 && y < grid.length - 1;
    }

    private boolean isWall(Cell[][] grid, int x, int y) {
        return grid[y][x].type() == CellType.WALL;
    }
}
