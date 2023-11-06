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
import java.time.OffsetDateTime;
import java.util.stream.Stream;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) throws IOException {
        String [] testArgs = new String [] {
            "C:\\Users\\striz\\IdeaProjects\\project-template\\nginxLogs.txt",
            "2015-05-17",
            "adoc"
        };
        ArgumentsContainer argumentsContainer = ArgumentsParser.parse(testArgs);
        Path writeFilePath = argumentsContainer.file().toPath();
        String firstLog = "93.180.71.3 - - [17/May/2015:08:05:32 +1000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        LogAnalyzer parser = new LogAnalyzer();
        Statistic log = parser.analyze(argumentsContainer.file(),argumentsContainer.from(),argumentsContainer.to());
        System.out.println(log);
    }
}
