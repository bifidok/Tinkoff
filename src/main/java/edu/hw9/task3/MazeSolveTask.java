package edu.hw9.task3;

import edu.hw9.task3.project2.Cell;
import edu.hw9.task3.project2.CellType;
import edu.hw9.task3.project2.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class MazeSolveTask extends RecursiveTask<Set<Cell>> {
    private final Set<Cell> visitedCells;
    private final Cell currentCell;
    private final Maze maze;
    private final int[][] waysToGo = new int[][] {
        {0, 1},
        {-1, 0},
        {1, 0},
        {0, -1},
    };

    public MazeSolveTask(Set<Cell> visitedCells, Maze maze, Cell currentCell) {
        this.visitedCells = visitedCells;
        this.currentCell = currentCell;
        this.maze = maze;
    }

    @Override
    protected Set<Cell> compute() {
        visitedCells.add(currentCell);
        Cell[][] grid = maze.getGrid();
        if (currentCell.equals(maze.getEnd())) {
            return visitedCells;
        } else {
            List<MazeSolveTask> tasks = new ArrayList<>();
            for (int[] way : waysToGo) {
                int neighbourX = currentCell.x() + way[0];
                int neighbourY = currentCell.y() + way[1];
                if (isInBounds(grid, new Cell(neighbourX, neighbourY, CellType.EMPTY))) {
                    Cell neighbour = grid[neighbourY][neighbourX];
                    if (!isWall(grid, neighbour) && !visitedCells.contains(neighbour)) {
                        MazeSolveTask task = new MazeSolveTask(
                            visitedCells,
                            maze,
                            neighbour
                        );
                        task.fork();
                        tasks.add(task);
                    }
                }
            }
            for (var task : tasks) {
                var path = task.join();
                if (path != null) {
                    return path;
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
