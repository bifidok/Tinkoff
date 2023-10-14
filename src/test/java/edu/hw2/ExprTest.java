package edu.hw2;

import edu.hw2.Task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExprTest {
    @Test
    @DisplayName("Базовый тест")
    void expr_shouldCorrectlyEvaluate_basicTest() {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));

        assertThat(res.evaluate()).isEqualTo(37.0);
    }
    @Test
    @DisplayName("Отрицательное число в Negate")
    void expr_shouldEvaluateMinusOne_whenValueAlreadyNagative() {
        var negOne = new Expr.Negate(new Expr.Constant(-1));

        assertThat(negOne.evaluate()).isEqualTo(1);
    }
    @Test
    @DisplayName("Отрицательные числа в Exponent")
    void expr_shouldExponentCorrectly_whenValueAndExponentNegative() {
        var negOne = new Expr.Exponent(new Expr.Constant(-2),-5);

        assertThat(negOne.evaluate()).isEqualTo(-0.03125);
    }
}
