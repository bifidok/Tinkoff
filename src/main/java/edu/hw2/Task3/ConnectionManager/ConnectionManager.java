package edu.hw2.Task3.ConnectionManager;

import edu.hw2.Task3.Connection.Connection;

public interface ConnectionManager {
    Connection getConnection();
    default boolean isFaultyConnection(){
        return Math.random() > 0.5;
    }
}
