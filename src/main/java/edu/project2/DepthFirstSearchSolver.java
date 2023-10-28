package edu.project2;

import java.util.HashSet;
import java.util.Set;

public class DepthFirstSearchSolver {
    private final int[][] waysToGo = new int[][] {
        {0, 1},
        {-1, 0},
        {1, 0},
        {0, -1},
    };
    private Set<Cell> visitedCells;

    public Set<Cell> solve(Maze maze) {
        visitedCells = new HashSet<>(maze.getHeight() * maze.getWidth());
        visitedCells.add(maze.getStart());
        Cell startFrom = maze.getGrid()[maze.getStart().y()][maze.getStart().x() + 1];
        return findEnd(visitedCells, maze.getGrid(), startFrom, maze.getEnd());
    }

    private Set<Cell> findEnd(Set<Cell> visitedCells, Cell[][] grid, Cell currentCell, Cell end) {
        if (!isInBounds(grid, currentCell) || isWall(grid, currentCell)
            || visitedCells.contains(currentCell)) {
            return null;
        }
        visitedCells.add(currentCell);
        if (currentCell.equals(end)) {
            return visitedCells;
        } else {
            for (int[] way : waysToGo) {
                var ans = findEnd(
                    visitedCells,
                    grid,
                    grid[currentCell.y() + way[1]][currentCell.x() + way[0]],
                    end
                );
                if (ans != null) {
                    return ans;
                }
            }
            visitedCells.remove(currentCell);
            return null;
        }
    }

    private boolean isInBounds(Cell[][] grid, Cell cell) {
        return cell.x() >= 0 && cell.y() >= 0
            && cell.x() < grid[0].length && cell.y() < grid.length;
    }

    private boolean isWall(Cell[][] grid, Cell cell) {
        return grid[cell.y()][cell.x()].type().equals(CellType.WALL);
    }
}
