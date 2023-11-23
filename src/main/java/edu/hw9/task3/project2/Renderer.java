package edu.hw9.task3.project2;

import java.util.Set;

public interface Renderer {
    String render(Maze maze, Set<Cell> path);
}
