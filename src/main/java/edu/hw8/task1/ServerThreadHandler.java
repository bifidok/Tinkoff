package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerThreadHandler implements Runnable {
    private final static Logger LOGGER = LogManager.getLogger();

    private final Repository quoteRepository;
    private final Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ServerThreadHandler(Socket client) {
        this.client = client;
        quoteRepository = new QuoteRepository();
    }

    @Override
    public void run() {
        LOGGER.info("");
        LOGGER.info("New connection opened");
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            while (!client.isClosed()) {
                String request = reader.readLine();
                if (request.equals("exit")) {
                    break;
                }
                LOGGER.info("Сервер получил: " + request);
                String quote = quoteRepository.findByKeyWord(request);
                if (quote == null) {
                    break;
                }
                writer.write(quote + '\n');
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
                writer.close();
                client.close();
                LOGGER.info("Connection closed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
