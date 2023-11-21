package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String EXIT_COMMAND = "exit";

    private final String[] requests;
    private Socket client;
    private BufferedWriter writer;
    private BufferedReader reader;
    private BufferedReader consoleReader;
    private int port;

    public Client(int port, String[] requests) {
        this.port = port;
        this.requests = requests;
    }

    public void start() {
        try {
            client = new Socket("localhost", port);
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
                    request = EXIT_COMMAND;
                }
                writer.write(request + '\n');
                writer.flush();
                LOGGER.info("Клиент отправил: " + request);
                if (request.equals(EXIT_COMMAND)) {
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
