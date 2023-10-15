package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.StableConnection;
import edu.hw2.task3.connectionmanager.ConnectionManager;
import edu.hw2.task3.connectionmanager.DefaultConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PopularCommandExecutorTest {
    private static final int MAX_ATTEMPS = 3;

    private ConnectionManager connectionManager = mock(DefaultConnectionManager.class);

    private PopularCommandExecutor executor = new PopularCommandExecutor(connectionManager, MAX_ATTEMPS);

    @Test
    @DisplayName("Проверка на выполнение команды и закрытие соединения")
    void updatePackages_shouldExecuteCommandAndCloseConnection() throws Exception {
        String command = "apt update && apt upgrade -y";
        Connection connection = mock(StableConnection.class);
        when(connectionManager.getConnection()).thenReturn(connection);

        executor.updatePackages();

        verify(connection, times(1)).execute(command);
        verify(connection, times(1)).close();
    }
}
