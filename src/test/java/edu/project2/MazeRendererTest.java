package edu.project2;

import edu.project2.generator.MazeGenerator;
import edu.project2.generator.PrimGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MazeRendererTest {
    @Test
    @DisplayName("Отрисовка заранее известного лабиринта")
    public void print_shouldCorrectlyPrint(){
        String passage = "\u001b[0m   \u001b[32m";
        String wall = "   ";
        MazeGenerator generator = new PrimGenerator();
        Maze maze = generator.generate(15,20);
        Renderer renderer = new MazeRenderer();

        StringBuilder mazeOutputExpected = new StringBuilder();
        mazeOutputExpected.append("\n");
        for(Cell [] row : maze.getGrid()){
            for (Cell cell : row){
                switch (cell.type()){
                    case EMPTY -> mazeOutputExpected.append(passage);
                    case WALL -> mazeOutputExpected.append(wall);
                }
            }
            mazeOutputExpected.append("\n");
        }
        var mazeOutputActual = renderer.render(maze,new HashSet<>());

        assertThat(mazeOutputActual).isEqualTo(mazeOutputExpected.toString());
    }
}
