package edu.project2.solver;

import edu.project2.Cell;
import edu.project2.CellType;
import edu.project2.Maze;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepthFirstSearchSolver implements MazeSolver {
    private final int[][] waysToGo = new int[][] {
        {0, 1},
        {-1, 0},
        {1, 0},
        {0, -1},
    };
    private Map<Cell, Integer> visitedCells;

    public List<Cell> solve(Maze maze) {
        visitedCells = new HashMap<>(maze.getHeight() * maze.getWidth());
        int startCellNumber = 1;
        visitedCells.put(maze.getStart(), startCellNumber);
        Cell startFrom = maze.getGrid()[maze.getStart().y()][maze.getStart().x() + 1];
        return findEnd(visitedCells, maze.getGrid(), startFrom, maze.getEnd(), startCellNumber + 1);
    }

    private List<Cell> findEnd(
        Map<Cell, Integer> visitedCells,
        Cell[][] grid,
        Cell currentCell,
        Cell end,
        int cellNumber
    ) {
        if (!isInBounds(grid, currentCell) || isWall(grid, currentCell)
            || visitedCells.containsKey(currentCell)) {
            return null;
        }
        visitedCells.put(currentCell, cellNumber);
        if (currentCell.equals(end)) {
            return convertPathToList(visitedCells);
        } else {
            for (int[] way : waysToGo) {
                var ans = findEnd(
                    visitedCells,
                    grid,
                    grid[currentCell.y() + way[1]][currentCell.x() + way[0]],
                    end,
                    cellNumber + 1
                );
                if (ans != null) {
                    return ans;
                }
            }
            visitedCells.remove(currentCell);
            return null;
        }
    }

    private List<Cell> convertPathToList(Map<Cell, Integer> answerPath) {
        Cell[] convertedAnswerPath = new Cell[answerPath.size()];
        for (var entry : answerPath.entrySet()) {
            convertedAnswerPath[entry.getValue() - 1] = entry.getKey();
        }
        return Arrays.asList(convertedAnswerPath);
    }

    private boolean isInBounds(Cell[][] grid, Cell cell) {
        return cell.x() > 0 && cell.y() > 0
            && cell.x() < grid[0].length && cell.y() < grid.length;
    }

    private boolean isWall(Cell[][] grid, Cell cell) {
        return grid[cell.y()][cell.x()].type().equals(CellType.WALL);
    }
}
