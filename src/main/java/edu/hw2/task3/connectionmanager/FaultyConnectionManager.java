package edu.hw2.task3.connectionmanager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;

public class FaultyConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        return new FaultyConnection();
    }
}
