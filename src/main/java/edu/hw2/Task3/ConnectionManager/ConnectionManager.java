package edu.hw2.Task3.ConnectionManager;

import edu.hw2.Task3.Connection.Connection;

public interface ConnectionManager {
    double PROBABLITY_BOUND = 0.5;

    Connection getConnection();

    default boolean isFaultyConnection() {
        return Math.random() > PROBABLITY_BOUND;
    }
}
