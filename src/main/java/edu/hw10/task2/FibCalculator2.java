package edu.hw10.task2;

public interface FibCalculator2 {
    @Cache(persist = true)
    long calculate(int number);
    @Cache(persist = false)
    long ahahahah(int number);
}
