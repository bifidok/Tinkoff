package edu.hw8.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    private final static Logger LOGGER = LogManager.getLogger();
    private static Socket client;
    private static BufferedWriter writer;
    private static BufferedReader reader;
    private static BufferedReader consoleReader;
    private final int PORT;
//    int port = 8080;
//    Server server = new Server(port,1);
//    Client client = new Client(port);
//    Client client2 = new Client(port);
//    Client client3 = new Client(port);
//    Thread serverThread = new Thread(() -> {
//        try {
//            server.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    });
//    Thread clientThread = new Thread(() -> {
//        try {
//            client.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    });
//    Thread clientThread2 = new Thread(() -> {
//        try {
//            client2.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    });
//    Thread clientThread3 = new Thread(() -> {
//        try {
//            client3.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    });
//        serverThread.start();
//        clientThread.start();
//        clientThread2.start();
//        clientThread3.start();
//        clientThread.join();
//        clientThread2.join();
//        clientThread3.join();
//        server.close();
//        serverThread.join();
//        System.out.println("closed");

    public Client(int port) {
        PORT = port;
    }

    public void start() throws IOException {
        client = new Socket("localhost", PORT);
        try {
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            String [] inputs = {"qwe","exit"};
            int i = 0;
            while (!client.isClosed()) {
                LOGGER.info("(Client " + Thread.currentThread().getName() + ") Введите запрос");
                //String request = reader.readLine();
                Thread.sleep(1000);
                String request = inputs[i];
                System.out.println("client writing...");
                writer.write(request + '\n');
                writer.flush();
                System.out.println("client wrote " + request);
                if (request.equals("exit")) {
                    System.out.println("exit client");
                    break;
                }
                String response = reader.readLine();
                LOGGER.info("Клиент получил: " + response);
                i++;
            }
        } catch (IOException exp) {
            LOGGER.info(exp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            consoleReader.close();
            LOGGER.info("Client end");
        }
    }
}
