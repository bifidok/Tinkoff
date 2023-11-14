package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class FileCloner {
    private final static Pattern COPIED_FILE_PATTERN
        = Pattern.compile("^(?<name>.*) - копия(\\((?<copyCount>\\d+)\\))?$");

    public Path cloneFile(Path path) {
        File file = new File(path.toString());
        Path clonedPath = path;
        while (file.exists()) {
            clonedPath = updateFileName(clonedPath);
            file = new File(clonedPath.toString());
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file.toPath();
    }

    @SuppressWarnings("MultipleStringLiterals")
    private Path updateFileName(Path path) {
        String fullFileName = path.getName(path.getNameCount() - 1).toString();
        String fileName;
        String extension;
        int point = fullFileName.length() - 1;
        for (; point >= 0; point--) {
            if (fullFileName.charAt(point) == '.') {
                break;
            }
        }
        fileName = fullFileName.substring(0, point);
        extension = fullFileName.substring(point + 1);
        var matcher = COPIED_FILE_PATTERN.matcher(fileName);
        if (matcher.matches()) {
            int copyCount;
            if (matcher.group("copyCount") != null) {
                copyCount = Integer.parseInt(matcher.group("copyCount"));
                copyCount++;
            } else {
                copyCount = 2;
            }
            fileName = String.format("%s - копия(%d).%s", matcher.group("name"), copyCount, extension);
        } else {
            fileName = String.format("%s - копия.%s", fileName, extension);
        }
        String pathWithoutFileName = path.toString().substring(0, path.toString().length() - fullFileName.length());
        return Path.of(pathWithoutFileName + fileName);
    }
}
