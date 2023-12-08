package edu.hw10.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        FibCalculator calc = new Calculator();
        FibCalculator proxy = CacheProxy.create(calc,calc.getClass());
        proxy.fib(5);
        proxy.fibFalse(5);
        FibCalculator2 calc2 = new Calculator2();
        FibCalculator2 proxy2 = CacheProxy.create(calc2,calc2.getClass());
        proxy2.calculate(5);
        proxy2.ahahahah(5);
    }
}
