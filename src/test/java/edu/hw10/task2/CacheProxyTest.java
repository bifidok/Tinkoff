package edu.hw10.task2;

import edu.hw10.task2.testClasses.FibCalculator;
import edu.hw10.task2.testClasses.SimpleCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CacheProxyTest {
    @Test
    @DisplayName("Вычисление Фибоначчи без сохранения")
    public void create_whenNoPersist(){
        Path cachePath = Path.of("src/test/java/edu/hw10/task2/cache");
        FibCalculator calculator = new SimpleCalculator();
        FibCalculator proxy = CacheProxy.create(calculator,calculator.getClass(),cachePath);

        long expected = 5;
        long actual = proxy.fibNoCache((int) expected);
        Method method = null;
        try {
            method = calculator.getClass().getDeclaredMethod("fib",int.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        String key = method.getName() + Arrays.toString(method.getParameters());
        Path cacheFile = Path.of(cachePath + "/" + key + ".txt");

        assertThat(actual).isEqualTo(expected);
        assertThat(cacheFile.toFile().exists()).isFalse();
    }
    @Test
    @DisplayName("Сохранение результата числа Фибоначчи")
    public void create_whenPersist(){
        Path cachePath = Path.of("src/test/java/edu/hw10/task2/cache");
        FibCalculator calculator = new SimpleCalculator();
        FibCalculator proxy = CacheProxy.create(calculator,calculator.getClass(),cachePath);

        long expected = 5;
        long actual = proxy.fib((int) expected);
        Method method = null;
        try {
            method = calculator.getClass().getDeclaredMethod("fib",int.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        String key = method.getName() + Arrays.toString(method.getParameters());
        Path cacheFile = Path.of(cachePath + "/" + key + ".txt");

        assertThat(actual).isEqualTo(expected);
        assertThat(cacheFile.toFile().exists()).isTrue();
        cacheFile.toFile().delete();
    }
}
