package edu.hw9.task1.callable;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class AverageCallable implements Callable<Double> {
    private double [] data;
    public AverageCallable(double [] data) {
        this.data = data;
    }

    @Override
    public Double call() throws Exception {
        return Arrays.stream(data).average().orElse(0);
    }
}
