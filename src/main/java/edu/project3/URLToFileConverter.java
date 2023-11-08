package edu.project3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class URLToFileConverter {
    private final static String FILE_PATH = "C:\\Users\\striz\\IdeaProjects\\project-template\\nginxLogs.txt";

    private URLToFileConverter() {
    }

    public static File convert(String urlString) {
        Path path = Paths.get(FILE_PATH);
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException exception) {
            throw new RuntimeException(exception);
        }
        try (ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(path.toString());
             FileChannel fileChannel = fileOutputStream.getChannel()) {
            fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new File(FILE_PATH);
    }
}
