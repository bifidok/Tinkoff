package edu.project2;

import java.util.Arrays;

public class Maze {
    private final int width;
    private final int height;
    private final Cell [][] grid;

    public Maze(int width, int height, Cell[][] grid) {
        this.width = width;
        this.height = height;
        this.grid = grid;
    }

    public CellType getCellType(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height) return null;
        return grid[y][x].type();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getGrid() {
        return Arrays.stream(grid)
            .map(Cell[]::clone)
            .toArray(Cell[][]::new);
    }
}
