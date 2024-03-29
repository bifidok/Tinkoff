package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class FixedThreadPool implements ThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final ReentrantLock mainLock = new ReentrantLock();
    private final List<Worker> workers;
    private int maxPoolSize;
    private boolean isStopped = false;

    private FixedThreadPool(int threadCount) {
        maxPoolSize = threadCount;
        taskQueue = new LinkedBlockingQueue<>();
        workers = new ArrayList<>();
    }

    public static FixedThreadPool create(int threadCount) {
        return new FixedThreadPool(threadCount);
    }

    @Override
    public void start() {
        for (int i = 0; i < maxPoolSize; i++) {
            workers.add(new Worker(taskQueue));
        }
        for (Worker worker : workers) {
            new Thread(worker).start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (!isStopped) {
            taskQueue.offer(runnable);
        }
    }

    @Override
    public void close() {
        mainLock.lock();
        try {
            if (!isStopped) {
                isStopped = true;
                interruptWorkers();

            }
        } finally {
            mainLock.unlock();
        }
    }

    private void interruptWorkers() {
        for (Worker worker : workers) {
            worker.stop();
        }
    }
}
