package edu.project3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        String [] testArgs = new String [] {
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            "2023-08-31",
            "2023-08-31",
            "adoc"
        };
        ArgumentsContainer argumentsContainer = ArgumentsParser.parse(testArgs);
        try (Stream<String> stream = Files.lines(argumentsContainer.file().toPath(), StandardCharsets.UTF_8)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
