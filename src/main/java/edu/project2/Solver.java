package edu.project2;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private final int[][] waysToGo = new int[][] {
        {0, 1},
        {-1, 0},
        {1, 0},
        {0, -1},
    };

    public List<Cell> solve(Maze maze) {
        List<Cell> answer = new ArrayList<>(maze.getHeight() * maze.getWidth());
        answer.add(maze.getStart());
        Cell startFrom = maze.getGrid()[maze.getStart().y()][maze.getStart().x() + 1];
        return findEnd(answer, maze.getGrid(), startFrom, maze.getEnd());
    }

    private List<Cell> findEnd(List<Cell> list, Cell[][] grid, Cell currentCoordinate, Cell end) {
        if (currentCoordinate.x() < 0 || currentCoordinate.y() < 0
            || currentCoordinate.x() > grid[0].length - 1 || currentCoordinate.y() > grid.length - 1
            || grid[currentCoordinate.y()][currentCoordinate.x()].type().equals(CellType.WALL)
            || list.contains(currentCoordinate)) {
            return null;
        }
        list.add(currentCoordinate);
        if (currentCoordinate.equals(end)) {
            return list;
        } else {
            for (int[] toGo : waysToGo) {
                var ans = findEnd(
                    list,
                    grid,
                    grid[currentCoordinate.y() + toGo[1]][currentCoordinate.x() + toGo[0]],
                    end
                );
                if (ans != null) {
                    return ans;
                }
            }
            list.remove(list.size() - 1);
            return null;
        }
    }
}
