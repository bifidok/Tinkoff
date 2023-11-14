package edu.hw6.DirectoryFilter;

import java.io.IOException;
import java.nio.file.Path;

public class Extension extends AbstractFilter {
    private String extension;

    public Extension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        String fullFileName = String.valueOf(entry.getFileName());
        String fileExtension = "";
        for (int i = fullFileName.length() - 1; i >= 0; i--) {
            if (fullFileName.charAt(i) == '.') {
                fileExtension = fullFileName.substring(i + 1);
                break;
            }
        }
        return extension.equals(fileExtension) && (next != null ? next.accept(entry) : true);
    }
}
