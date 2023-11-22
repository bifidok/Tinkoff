package edu.hw9.task1;

import edu.hw9.task1.callable.AverageCallable;
import edu.hw9.task1.callable.MaxCallable;
import edu.hw9.task1.callable.MinCallable;
import edu.hw9.task1.callable.SumCallable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector {
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Map<String,Metric> metrics;

    public StatsCollector() {
        metrics = new HashMap<>();
    }

    public void push(String metricName, double [] data){
        lock.writeLock().lock();
        try {
            Future<Double> sumFuture = executorService.submit(new SumCallable(data));
            Future<Double> averageFuture = executorService.submit(new AverageCallable(data));
            Future<Double> maxFuture = executorService.submit(new MaxCallable(data));
            Future<Double> minFuture = executorService.submit(new MinCallable(data));
            try {
                double sum = sumFuture.get();
                double average = averageFuture.get();
                double max = maxFuture.get();
                double min = minFuture.get();
                metrics.put(metricName, new Metric(sum, average, max, min));
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }finally {
            lock.writeLock().unlock();
        }
    }

    public Set<Map.Entry<String, Metric>> stats(){
        lock.readLock().lock();
        Set<Map.Entry<String, Metric>> entries;
        try {
            entries = metrics.entrySet();
        }finally {
            lock.readLock().unlock();
        }
        return entries;
    }
    public void close(){
        executorService.shutdown();
    }
}
