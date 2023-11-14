package edu.hw6.DirectoryFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Writable extends AbstractFilter {
    @Override
    public boolean accept(Path entry) throws IOException {
        return new File(entry.toString()).canWrite() && (next != null ? next.accept(entry) : true);
    }
}
