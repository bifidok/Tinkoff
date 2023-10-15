package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }
    }

    public record Negate(Expr value) implements Expr {
        @Override
        public double evaluate() {
            return value.evaluate() * -1;
        }
    }

    record Exponent(Expr value, double exponent) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(value.evaluate(), exponent);
        }
    }

    record Addition(Expr value1, Expr value2) implements Expr {
        @Override
        public double evaluate() {
            return value1.evaluate() + value2.evaluate();
        }
    }

    record Multiplication(Expr value1, Expr value2) implements Expr {
        @Override
        public double evaluate() {
            return value1.evaluate() * value2.evaluate();
        }
    }
}
