package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class DirectoryTask extends RecursiveTask<List<Path>> {
    private final static int FILES_COUNT_REQUIRED = 1000;
    private final Path rootPath;
    private final List<Path> directoriesWithRequiredFilesCount;

    public DirectoryTask(Path path, List<Path> directoriesWithRequiredFilesCount) {
        this.rootPath = path;
        this.directoriesWithRequiredFilesCount = directoriesWithRequiredFilesCount;
    }

    @Override
    protected List<Path> compute() {
        List<Path> pathContent;
        try {
            pathContent = Files.list(rootPath).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Path> files = new ArrayList<>();
        List<Path> directories = new ArrayList<>();

        for (Path path : pathContent) {
            if (Files.isDirectory(path)) {
                directories.add(path);
            } else if (Files.isRegularFile(path)) {
                files.add(path);
            }
        }

        List<DirectoryTask> subTasks = new ArrayList<>();

        for (Path directory : directories) {
            DirectoryTask task = new DirectoryTask(directory, directoriesWithRequiredFilesCount);
            task.fork();
            subTasks.add(task);
        }

        for (DirectoryTask task : subTasks) {
            files.addAll(task.join());
        }
        if (files.size() > FILES_COUNT_REQUIRED) {
            directoriesWithRequiredFilesCount.add(rootPath);
        }
        return files;
    }
}
