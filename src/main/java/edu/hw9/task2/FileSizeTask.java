package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileSizeTask extends RecursiveTask<List<Path>> {
    private final Path path;
    private final int size;

    public FileSizeTask(Path path, int size) {
        this.path = path;
        this.size = size;
    }

    @Override
    protected List<Path> compute() {
        List<Path> pathContent;
        try {
            pathContent = Files.list(path).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Path> files = new ArrayList<>();
        List<Path> directories = new ArrayList<>();

        for(Path path : pathContent){
            if(Files.isDirectory(path)){
                directories.add(path);
            }else if(Files.isRegularFile(path)){
                files.add(path);
            }
        }

        List<FileSizeTask> subTasks = new ArrayList<>();

        for (Path directory : directories) {
            FileSizeTask task = new FileSizeTask(directory,size);
            task.fork();
            subTasks.add(task);
        }

        List<Path> filesWithRequiredSize = new ArrayList<>();

        for (var path : files){
            if(path.toFile().length() == size){
                filesWithRequiredSize.add(path);
            }
        }
        for (FileSizeTask task : subTasks) {
            filesWithRequiredSize.addAll(task.join());
        }

        return filesWithRequiredSize;
    }
}
