package edu.hw3.task7;

import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NullComparatorTest {
    @Test
    @DisplayName("Null в TreeMap")
    public void nullComparator_shouldAddNullKey() {
        TreeMap<String, String> treeMap = new TreeMap<>(new NullComparator<>());

        treeMap.put("fsd", "dsa");
        treeMap.put(null, "null");

        assertThat(treeMap.containsKey(null)).isTrue();
    }
}
