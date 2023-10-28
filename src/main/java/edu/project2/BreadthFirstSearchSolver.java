package edu.project2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearchSolver {
    private final int[][] waysToGo = new int[][] {
        {0, 1},
        {-1, 0},
        {1, 0},
        {0, -1},
    };

    public Set<Cell> solve(Maze maze) {
        return findEnd(maze.getGrid(), maze.getStart(), maze.getEnd());
    }

    private Set<Cell> findEnd(Cell[][] grid, Cell start, Cell end) {
        Map<Cell, Cell> cellToPrev = new HashMap<>();
        Queue<Cell> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            if (cell.equals(end)) {
                return createAnswerPath(cellToPrev, cell);
            }
            for (int i = 0; i < waysToGo.length; i++) {
                int neighbourX = cell.x() + waysToGo[i][0];
                int neighbourY = cell.y() + waysToGo[i][1];
                if (isInBounds(grid, new Cell(neighbourX, neighbourY, CellType.EMPTY))) {
                    Cell neighbour = grid[neighbourY][neighbourX];
                    if (!isWall(grid, neighbour)
                        && !cellToPrev.containsKey(neighbour)) {
                        queue.add(neighbour);
                        cellToPrev.put(neighbour, cell);
                    }

                }
            }
        }
        return null;
    }

    private Set<Cell> createAnswerPath(Map<Cell, Cell> cellToPrev, Cell end) {
        Set<Cell> answerPath = new HashSet<>();
        Cell curCell = end;
        while (cellToPrev.containsKey(curCell)) {
            answerPath.add(curCell);
            Cell prevCell = cellToPrev.get(curCell);
            cellToPrev.remove(curCell);
            curCell = prevCell;
        }
        return answerPath;
    }

    private boolean isInBounds(Cell[][] grid, Cell cell) {
        return cell.x() >= 0 && cell.y() >= 0
            && cell.x() < grid[0].length && cell.y() < grid.length;
    }

    private boolean isWall(Cell[][] grid, Cell cell) {
        return grid[cell.y()][cell.x()].type().equals(CellType.WALL);
    }

}
