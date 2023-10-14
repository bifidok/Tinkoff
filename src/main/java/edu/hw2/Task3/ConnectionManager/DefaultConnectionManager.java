package edu.hw2.Task3.ConnectionManager;

import edu.hw2.Task3.Connection.Connection;
import edu.hw2.Task3.Connection.FaultyConnection;
import edu.hw2.Task3.Connection.StableConnection;

public class DefaultConnectionManager implements ConnectionManager{
    @Override
    public Connection getConnection() {
        if(isFaultyConnection()) return new FaultyConnection();
        return new StableConnection();
    }
}
