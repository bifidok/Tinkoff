package edu.hw2.task3.connection;

public interface Connection extends AutoCloseable {
    double PROBABLITY_BOUND = 0.5;

    void execute(String command);

    default boolean isConnectionFailed() {
        return Math.random() > PROBABLITY_BOUND;
    }
}
