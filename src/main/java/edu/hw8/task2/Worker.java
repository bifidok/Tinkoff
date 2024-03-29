package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Worker implements Runnable {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int WAIT_FOR_TASK_TIMEOUT = 1000;

    private final BlockingQueue<Runnable> queue;
    private boolean isStopped = false;

    public Worker(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!isStopped) {
            try {
                Runnable runnable = queue.poll(WAIT_FOR_TASK_TIMEOUT, TimeUnit.NANOSECONDS);
                if (runnable != null) {
                    runnable.run();
                }
            } catch (InterruptedException exception) {
                LOGGER.warn("Worker " + Thread.currentThread().getName() + " interrupted");
            }
        }
    }

    public void stop() {
        isStopped = true;
        Thread.currentThread().interrupt();
    }
}
