package edu.hw2.task3.connectionmanager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;

public class DefaultConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        if (isFaultyConnection()) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
