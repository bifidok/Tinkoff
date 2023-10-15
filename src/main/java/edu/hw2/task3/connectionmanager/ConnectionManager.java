package edu.hw2.task3.connectionmanager;

import edu.hw2.task3.connection.Connection;

public interface ConnectionManager {
    double PROBABLITY_BOUND = 0.5;

    Connection getConnection();

    default boolean isFaultyConnection() {
        return Math.random() > PROBABLITY_BOUND;
    }
}
