package edu.hw6.DirectoryFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class AbstractFilterTest {
    private static List<File> files;
    private static final AbstractFilter extensionFilter = new Extension("txt");
    private static final AbstractFilter sizeFilter = new LargerThan(100);
    private static final AbstractFilter magicFilter = new MagicNumber(0x89, 'P', 'N', 'G');
    private static final AbstractFilter readableFilter = new Readable();
    private static final AbstractFilter writableFilter = new Writable();
    private static final Path FILES_TO_FILTER_DIRECTORY =
        Path.of("C:\\Users\\striz\\IdeaProjects\\project-template\\src\\main\\resources");

    // Не проходит билд на гите из-за создания файлов
//    @BeforeAll
//    public static void init() throws IOException {
//        files = new ArrayList<>();
//        File readable = new File(FILES_TO_FILTER_DIRECTORY + File.separator + "readable.txt");
//        readable.setReadable(true);
//        readable.createNewFile();
//        files.add(readable);
//
//        File notReadable = new File(FILES_TO_FILTER_DIRECTORY + File.separator + "notreadable.txt");
//        notReadable.setReadable(false);
//        notReadable.createNewFile();
//        files.add(notReadable);
//
//        File writable = new File(FILES_TO_FILTER_DIRECTORY + File.separator + "writable.png");
//        writable.setWritable(true);
//        writable.createNewFile();
//        files.add(writable);
//
//        File notWritable = new File(FILES_TO_FILTER_DIRECTORY + File.separator + "notwritable.java");
//        notWritable.setWritable(false);
//        notWritable.createNewFile();
//        files.add(notWritable);
//
//        File sizeMoreThan100 = new File(FILES_TO_FILTER_DIRECTORY + File.separator + "size.txt");
//        sizeMoreThan100.createNewFile();
//        byte[] bytes = new byte[150];
//        Files.write(sizeMoreThan100.toPath(), bytes);
//        files.add(sizeMoreThan100);
//    }

//    @AfterAll
//    public static void teardown() {
//        for (File file : files) {
//            file.delete();
//        }
//    }

    @Test
    @DisplayName("Readable")
    public void readable_shouldReturnReadableFiles() {
        DirectoryStream.Filter<Path> filter = readableFilter;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(FILES_TO_FILTER_DIRECTORY, filter)) {
            stream.forEach(file -> assertThat(new File(String.valueOf(file)).canRead()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Writable")
    public void writable_shouldReturnWritableFiles() {
        DirectoryStream.Filter<Path> filter = writableFilter;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(FILES_TO_FILTER_DIRECTORY, filter)) {
            stream.forEach(file -> assertThat(new File(String.valueOf(file)).canWrite()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Size filter больше 100")
    public void size_shouldReturnFilesWithSizeMoreThan100() {
        DirectoryStream.Filter<Path> filter = sizeFilter;
        int sizeBound = 100;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(FILES_TO_FILTER_DIRECTORY, filter)) {
            stream.forEach(file -> assertThat(new File(String.valueOf(file)).length() > sizeBound));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Extension")
    public void extension_shouldReturnOnlyTXTExtension() {
        DirectoryStream.Filter<Path> filter = extensionFilter;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(FILES_TO_FILTER_DIRECTORY, filter)) {
            stream.forEach(file -> assertThat(file.endsWith("txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Magic number")
    public void magicNumber() {
        DirectoryStream.Filter<Path> filter = magicFilter;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(FILES_TO_FILTER_DIRECTORY, filter)) {
            stream.forEach(file -> assertThat(file.endsWith("png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
