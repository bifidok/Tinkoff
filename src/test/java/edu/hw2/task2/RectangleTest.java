package edu.hw2.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RectangleTest {
    @Test
    @DisplayName("Проверка LSP")
    public void squareArea_shouldReturnCorrectSquareArea() {
        Rectangle square = new Square(30);
        double area = square.area();
        assertThat(area).isEqualTo(900);
    }
    @Test
    @DisplayName("Проверка LSP2")
    public void squareArea2_shouldReturnCorrectSquareArea() {
        Rectangle square = new Square(30);
        double area = square.setHeight(30).setWidth(40).area();
        assertThat(area).isEqualTo(1200);
    }

    @Test
    @DisplayName("Квадрату устанавливается ширина и высота")
    public void squareArea_shouldReturnCorrectSquareArea_whenSquareHasWidthAndHeight() {
        Rectangle square = new Square(30);
        square = square.setHeight(99);
        square = square.setWidth(20);
        double area = square.area();
        assertThat(area).isEqualTo(1980);
    }

    @Test
    @DisplayName("Квадрату устанавливается ширина и высота, но объект не изменяется")
    public void squareArea_shouldReturnInitialSquareArea() {
        Rectangle square = new Square(30).setHeight(99);
        double area = square.area();
        assertThat(area).isEqualTo(2970);
    }

}
