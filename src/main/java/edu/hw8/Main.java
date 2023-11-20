package edu.hw8;

import edu.hw8.task1.Client;
import edu.hw8.task1.Server;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import edu.hw8.task2.FixedThreadPool;
import edu.hw8.task2.ThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    public static class MyThread extends Thread {
        String name;

        public MyThread(String name) {
            this.name = name;
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + name);
            }
        }
    }

    private Main() {
    }

    public static void main(String[] args) throws Exception {

        String hash = "098f6bcd4621d373cade4e832627b4f6";
        String pass = "tst";
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(pass.getBytes(StandardCharsets.UTF_8));
        byte [] diget = messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < diget.length; ++i) {
            sb.append(Integer.toHexString((diget[i] & 0xFF) | 0x100).substring(1,3));
        }
        System.out.println(sb);

//        ThreadPool threadPool = FixedThreadPool.create(1);
//        threadPool.start();
//        long time1 = System.nanoTime();
//        for(int i = 0; i < 100; i++){
//            threadPool.execute(new MyThread("A"));
//            threadPool.execute(new MyThread("B"));
//            threadPool.execute(new MyThread("C"));
//        }
//        Thread.sleep(1000);
//        threadPool.close();
//        System.out.println((System.nanoTime() - time1)/1000000);

//        int port = 8080;
//        Server server = new Server(port, 1);
//        Client client = new Client(port, new String[] {"личности", "оскробление"});
//        Client client2 = new Client(port, new String[] {"adf"});
//        Client client3 = new Client(port, new String[] {"ты"});
//        Thread serverThread = new Thread(() -> {
//            server.start();
//        });
//        Thread clientThread = new Thread(() -> {
//            client.start();
//        });
//        Thread clientThread2 = new Thread(() -> {
//            client2.start();
//        });
//        Thread clientThread3 = new Thread(() -> {
//            client3.start();
//        });
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
    }
}

