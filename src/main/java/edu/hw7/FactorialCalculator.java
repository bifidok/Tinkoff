package edu.hw7;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class FactorialCalculator {

    private FactorialCalculator() {
    }

    public static BigInteger factorial(int num) {
        if (num < 0) {
            return null;
        }
        if (num == 0) {
            return BigInteger.ONE;
        }
        return IntStream
            .rangeClosed(1, num)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger::multiply)
            .orElse(null);

    }
}
