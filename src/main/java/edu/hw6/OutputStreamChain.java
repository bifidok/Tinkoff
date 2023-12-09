package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class OutputStreamChain {
    private final static String QUOTE = "Programming is learned by writing programs. ― Brian Kernighan";

    private OutputStreamChain() {
    }

    public static void write(Path path) {
        try (OutputStream fileStream = Files.newOutputStream(path);
             CheckedOutputStream cos = new CheckedOutputStream(fileStream, new CRC32());
             BufferedOutputStream bos = new BufferedOutputStream(cos);
             OutputStreamWriter writer = new OutputStreamWriter(bos, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(writer)) {
            printWriter.write(QUOTE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
