package edu.hw8.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
    private final static Logger LOGGER = LogManager.getLogger();
    private static ExecutorService executorService;
    private static ServerSocket server;

    private final int MAX_THREADS = 10;
    private final int THREAD_POOL_COUNT;
    private final int PORT;

    public Server(int port, int threads) {
        THREAD_POOL_COUNT = Math.min(threads, MAX_THREADS);
        this.PORT = port;
    }

    public void start() throws IOException {
        executorService = Executors.newFixedThreadPool(THREAD_POOL_COUNT);
        try {
            server = new ServerSocket(PORT);
            while (!server.isClosed()) {
                Socket client = server.accept();
                Future future = executorService.submit(new ServerThreadHandler(client));
            }
        } catch (IOException exception) {
            LOGGER.warn("Server closed with exception");
        } finally {
            executorService.shutdown();
        }
    }

    public void close() {
        try {
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
