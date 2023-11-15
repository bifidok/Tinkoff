package edu.hw7;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Task2 {

    private Task2() {
    }

    public static BigInteger factorial(int num){
        return IntStream
            .rangeClosed(1,num)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger::multiply)
            .orElse(null);

    }
}
