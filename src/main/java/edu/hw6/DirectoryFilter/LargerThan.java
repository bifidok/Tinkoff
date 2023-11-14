package edu.hw6.DirectoryFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class LargerThan extends AbstractFilter {
    private int bytesBound;

    public LargerThan(int bytesBound) {
        this.bytesBound = bytesBound;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        return new File(entry.toString()).length() > bytesBound && (next != null ? next.accept(entry) : true);
    }
}
