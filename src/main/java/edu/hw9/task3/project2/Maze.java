package edu.hw9.task3.project2;

import java.util.Arrays;

public class Maze {
    private final int width;
    private final int height;
    private final Cell[][] grid;
    private final Cell start;
    private final Cell end;

    public Maze(int width, int height, Cell[][] grid, Cell start, Cell end) {
        this.width = width;
        this.height = height;
        this.grid = grid;
        this.start = start;
        this.end = end;
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

    public Cell getStart() {
        return start;
    }

    public Cell getEnd() {
        return end;
    }
}
