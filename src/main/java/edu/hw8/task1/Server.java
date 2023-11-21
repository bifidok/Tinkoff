package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static int MAX_THREADS = 10;
    private int threadPoolCount;
    private int port;
    private ServerSocket server;

    public Server(int port, int threads) {
        threadPoolCount = Math.min(threads, MAX_THREADS);
        this.port = port;
    }

    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolCount);
        try {
            server = new ServerSocket(port);
            while (!server.isClosed()) {
                Socket client = server.accept();
                executorService.execute(new ServerThreadHandler(client));
            }
        } catch (IOException exception) {
        } finally {
            executorService.shutdown();
            LOGGER.warn("Соединение прервано");
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
