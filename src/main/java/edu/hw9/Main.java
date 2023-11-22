package edu.hw9;

import edu.hw9.task2.ExtensionTask;
import edu.hw9.task2.FileSizeTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException, IOException {



//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        List<Path> found = new ArrayList<>();
//        DirectoryCheckTask directoryCheckTask = new DirectoryCheckTask(Path.of("C:\\Users\\striz\\IdeaProjects"),found);
//        forkJoinPool.execute(directoryCheckTask);
//        directoryCheckTask.join();
//        ExtensionTask extensionTask = new ExtensionTask(Path.of("src/main"),"txt");
//        forkJoinPool.execute(extensionTask);
//        var result = extensionTask.join();
//        for(var path : result){
//            System.out.println(path);
//        }
        //System.out.println(results);

//        StatsCollector statsCollector = new StatsCollector();
//        statsCollector.push("4",new double[]{1.2,2.0,3.5});
//        statsCollector.push("5",new double[]{1.9,4.0,7.9});
//        AtomicInteger i = new AtomicInteger(0);
//        for(int j = 0; j < 10000; j++){
//            new Thread(() -> {
//                statsCollector.push(String.valueOf(i.incrementAndGet()), new double[] {1.1, 2.3}); // 3.4
//            }).start();
//        }
//        Thread.sleep(1000);
//        statsCollector.close();
//        for(var metric : statsCollector.stats()){
//            System.out.println(metric);
//        }
//        System.out.println(statsCollector.stats().size());
    }
}
