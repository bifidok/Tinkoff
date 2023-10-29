package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frequency {
    private Frequency() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> frequency = new HashMap<>();
        for (T obj : list) {
            frequency.put(obj, frequency.getOrDefault(obj, 0) + 1);
        }
        return frequency;
    }
}
