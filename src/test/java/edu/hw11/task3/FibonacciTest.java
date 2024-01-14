package edu.hw11.task3;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FibonacciTest {

    @Test
    @DisplayName("Calculation Fibonacci number")
    public void fib_shouldCalcFibonacci()
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> dynamicClass = new ByteBuddy().subclass(Object.class)
            .name("Fibonacci")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC)
            .withParameter(long.class)
            .intercept(new Implementation.Simple(new FibonacciMethod()))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        Object dynamicInstance = dynamicClass.getDeclaredConstructor().newInstance();
        long input = 3;
        long expectedAnswer = fib(input);
        long actualAnswer = (long) dynamicClass.getMethod("fib", long.class).invoke(dynamicInstance, input);

        assertThat(expectedAnswer).isEqualTo(actualAnswer);
    }

    long fib(long n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
