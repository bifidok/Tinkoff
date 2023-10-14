package edu.hw2.Task3.Connection;

public class StableConnection implements Connection{
    @Override
    public void execute(String command) {
        System.out.println(command);
    }

    @Override
    public void close() {
        System.out.println("Connection closed!");
    }
}
