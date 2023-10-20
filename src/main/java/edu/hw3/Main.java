package edu.hw3;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import edu.hw3.task2.BracketClusterizer;
import edu.hw3.task4.RomanConverter;
import edu.hw3.task7.NullComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        String input = "(((((((((((((((((((((((((((((((((()";
        var ans = BracketClusterizer.clusterize(input);
        System.out.println(ans);
    }
}
