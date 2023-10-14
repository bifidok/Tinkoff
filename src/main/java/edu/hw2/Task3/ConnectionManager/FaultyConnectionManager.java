package edu.hw2.Task3.ConnectionManager;

import edu.hw2.Task3.Connection.Connection;
import edu.hw2.Task3.Connection.FaultyConnection;

public class FaultyConnectionManager implements ConnectionManager{
    @Override
    public Connection getConnection() {
        return new FaultyConnection();
    }
}
