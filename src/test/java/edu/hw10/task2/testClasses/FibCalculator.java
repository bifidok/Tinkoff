package edu.hw10.task2.testClasses;

import edu.hw10.task2.Cache;

public interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
    @Cache(persist = false)
    long fibNoCache(int number);
}
