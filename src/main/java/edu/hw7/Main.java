package edu.hw7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        int threadsMax = 5;
        double maxPogr = 0;
        int [] points = {10000,100000,1000000,10000000};
        for(int i = 0; i < points.length; i++){
            Task4MultiThread piMulti = new Task4MultiThread(threadsMax);
            double pi = piMulti.calculate(points[i]);
            double pogr = Math.abs(pi - Math.PI);
            System.out.println(points[i] + " точек -  погрешность " + pogr);
        }
    }
}
