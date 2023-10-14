package edu.hw2.Task3;

public class ConnectionException extends RuntimeException{
    public ConnectionException(){
        super("Connection cannot be established");
    }
}
