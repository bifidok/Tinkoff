package edu.hw2.Task3;

import edu.hw2.Task3.Connection.Connection;
import edu.hw2.Task3.ConnectionManager.ConnectionManager;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttemps;

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
            } catch (ConnectionException e) {
                if (attemps == maxAttemps - 1) {
                    System.out.println(e.getMessage());
                }
                attemps++;
            } finally {
                try {
                    connection.close();
                } catch (Exception e1) {
                    System.out.println(connection.toString() + " didnt close!");
                }
            }
        }
    }
}
