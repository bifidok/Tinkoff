package edu.hw8.task3.bruteForceMultiThread;

import edu.hw8.task3.DatabaseReader;
import edu.hw8.task3.PasswordGenerator;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BruteForceMultiThread {
    private final static String HASH_TYPE = "MD5";
    private int threadsCount;
    private int passwordMaxLength;
    private volatile Map<String, String> hackedDatabase;
    private Map<String, String> bruteForcedData;

    public BruteForceMultiThread(int threadsCount, int passwordMaxLength) {
        this.threadsCount = threadsCount;
        this.passwordMaxLength = passwordMaxLength;
    }

    public Map<String, String> start(Path databaseFile) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
        DatabaseReader databaseReader = new DatabaseReader();
        PasswordGenerator generator = new PasswordGenerator(passwordMaxLength);
        generator.start();
        hackedDatabase = databaseReader.read(databaseFile);
        bruteForcedData = new HashMap<>();
        for (int i = 0; i < threadsCount; i++) {
            executorService.execute(new BruteTask(hackedDatabase, HASH_TYPE, generator, bruteForcedData));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        return bruteForcedData;
    }
}
