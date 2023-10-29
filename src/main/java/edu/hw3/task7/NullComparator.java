package edu.hw3.task7;

import java.util.Comparator;

public final class NullComparator<T> implements Comparator<T> {

    private final Comparator<T> real;

    public NullComparator() {
        this.real = (Comparator<T>) Comparator.naturalOrder();
    }

    @Override
    public int compare(T a, T b) {
        if (a == null) {
            return (b == null) ? 0 : 1;
        } else if (b == null) {
            return -1;
        } else {
            return real.compare(a, b);
        }
    }
}
