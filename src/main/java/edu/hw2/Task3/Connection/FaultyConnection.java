package edu.hw2.Task3.Connection;

import edu.hw2.Task3.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        if (isConnectionFailed()) {
            throw new ConnectionException("Connection cannot be established", new Exception());
        }
        LOGGER.info(command);
    }

    @Override
    public void close() {
        LOGGER.info("Connection closed");
    }
}
