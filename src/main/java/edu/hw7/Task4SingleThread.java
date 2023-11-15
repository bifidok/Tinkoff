package edu.hw7;

import java.security.SecureRandom;

public class Task4SingleThread {
    private final double SQUARE_SIDE_LENGTH = 1;
    private final double CIRCLE_CENTER = SQUARE_SIDE_LENGTH / 2;
    private final double CIRCLE_RADIUS = SQUARE_SIDE_LENGTH / 2;

    public double calculate(int pointsCount) {
        SecureRandom random = new SecureRandom();
        int circleCount = 0;
        int totalCount = 0;
        double x;
        double y;
        while (totalCount < pointsCount) {
            x = random.nextDouble(0, SQUARE_SIDE_LENGTH);
            y = random.nextDouble(0, SQUARE_SIDE_LENGTH);
            if (isInCircle(x, y)) {
                circleCount++;
            }
            totalCount++;
        }
        return 4 * ((double)circleCount / (double)totalCount);
    }

    private boolean isInCircle(double x, double y) {
        return Math.pow(CIRCLE_CENTER - x, 2) + Math.pow(CIRCLE_CENTER - y, 2) <= CIRCLE_RADIUS * CIRCLE_RADIUS;
    }
}
