package edu.hw6.DirectoryFilter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class MagicNumber extends AbstractFilter {
    private int[] hex;

    public MagicNumber(int... hexs) {
        hex = new int[hexs.length];
        for (int i = 0; i < hex.length; i++) {
            hex[i] = hexs[i];
        }
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(entry.toString())) {
            for (int i = 0; i < hex.length; ++i) {
                if (inputStream.read() != hex[i]) {
                    return false;
                }
            }
        }
        return (next != null ? next.accept(entry) : true);
    }
}
