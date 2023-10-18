package edu.hw2.task3.connection;

import edu.hw2.task3.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    private final double PROBABILITY_BOUND = 0.8;

    @Override
    public void execute(String command) {
        if (isConnectionFailed()) {
            throw new ConnectionException("Connection cannot be established");
        }
        LOGGER.info(command);
    }

    @Override
    public void close() {
        LOGGER.info("Connection closed");
    }
    private boolean isConnectionFailed(){
        return Math.random() < PROBABILITY_BOUND;
    }
}
