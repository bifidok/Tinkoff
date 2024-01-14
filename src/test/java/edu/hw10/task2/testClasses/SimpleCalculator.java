package edu.hw10.task2.testClasses;

public class SimpleCalculator implements FibCalculator {
    @Override
    public long fib(int number) {
        return calculate(number);
    }

    @Override
    public long fibNoCache(int number) {
        return calculate(number);
    }

    private long calculate(int number) {
        int[] f = new int[number + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= number; ++i) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[f.length - 1];
    }
}
