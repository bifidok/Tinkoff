package edu.hw2.Task3.Connection;

import edu.hw2.Task3.ConnectionException;

public class FaultyConnection implements Connection{
    @Override
    public void execute(String command) {
        if(isConnectionFailed()){
            throw new ConnectionException();
        }
        System.out.println(command);
    }

    @Override
    public void close() {
        System.out.println("Connection closed");
    }
}
