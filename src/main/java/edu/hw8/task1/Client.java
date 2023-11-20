package edu.hw8.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.ArrayUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;

public class Client {
    private final static Logger LOGGER = LogManager.getLogger();

    private final int PORT;
    private final String[] requests;
    private Socket client;
    private BufferedWriter writer;
    private BufferedReader reader;
    private BufferedReader consoleReader;

    public Client(int port, String[] requests) {
        PORT = port;
        this.requests = requests;
    }

    public void start() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            client = new Socket("localhost", PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            int i = 0;
            while (!client.isClosed()) {
                String request;
                if (i < requests.length) {
                    request = requests[i];
                } else {
                    request = "exit";
                }
                writer.write(request + '\n');
                writer.flush();
                LOGGER.info("Клиент отправил: " + request);
                if (request.equals("exit")) {
                    break;
                }
                String response = reader.readLine();
                LOGGER.info("Клиент получил: " + response);
                i++;
            }
        } catch (IOException exp) {
            LOGGER.warn(exp);
        } finally {
            try {
                consoleReader.close();
                LOGGER.info("Client end");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
