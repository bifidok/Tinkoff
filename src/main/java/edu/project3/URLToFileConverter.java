package edu.project3;

import java.io.File;
import java.io.FileNotFoundException;
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
        if(urlString == null){
            throw new IllegalArgumentException("Null file");
        }
        ReadableByteChannel readableByteChannel = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannel = null;
        try {
            URL url = new URL(urlString);
            readableByteChannel = Channels.newChannel(url.openStream());
            Path path = Paths.get(FILE_PATH);
            fileOutputStream = new FileOutputStream(path.toString());
            fileChannel = fileOutputStream.getChannel();
            fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (readableByteChannel != null) {
                try {
                    readableByteChannel.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return new File(FILE_PATH);
    }
}
