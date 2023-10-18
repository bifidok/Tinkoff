package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connectionmanager.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttemps;
    private final static Logger LOGGER = LogManager.getLogger();

    public PopularCommandExecutor(ConnectionManager manager, int maxAttemps) {
        this.manager = manager;
        this.maxAttemps = maxAttemps;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        int attemps = 0;
        while (attemps < maxAttemps) {
            Connection connection = manager.getConnection();
            try {
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                if (attemps == maxAttemps - 1) {
                    throw new ConnectionException(e.getMessage(),e.getCause());
                }
                attemps++;
            } finally {
                try {
                    connection.close();
                } catch (Exception closeException) {
                    LOGGER.warn(connection.toString() + " didnt close!");
                }
            }
        }
    }
}
