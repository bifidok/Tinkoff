package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExtensionTask extends RecursiveTask<List<Path>> {
    private final Path path;
    private final String extension;

    public ExtensionTask(Path path,String extension) {
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<Path> compute() {
        Pattern pattern = Pattern.compile(".*\\." + extension + "$");
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

        List<ExtensionTask> subTasks = new ArrayList<>();

        for (Path directory : directories) {
            ExtensionTask task = new ExtensionTask(directory,extension);
            task.fork();
            subTasks.add(task);
        }

        List<Path> filesWithRequiredExtension = new ArrayList<>();
        for (var path : files){
            var matcher = pattern.matcher(path.getFileName().toString());
            if(matcher.matches()){
                filesWithRequiredExtension.add(path);
            }
        }

        for (ExtensionTask task : subTasks) {
            filesWithRequiredExtension.addAll(task.join());
        }

        return filesWithRequiredExtension;
    }
}
