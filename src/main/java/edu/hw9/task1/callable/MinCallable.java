package edu.hw9.task1.callable;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class MinCallable implements Callable<Double> {
    private double[] data;

    public MinCallable(double[] data) {
        this.data = data;
    }

    @Override
    public Double call() throws Exception {
        return Arrays.stream(data).min().orElse(0);
    }
}
