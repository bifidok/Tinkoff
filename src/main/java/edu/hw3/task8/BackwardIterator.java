package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private List<T> list;
    private int i;

    public BackwardIterator(List<T> list) {
        this.list = list;
        i = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return i >= 0;
    }

    @Override
    public T next() {
        if (hasNext()) {
            var element = list.get(i);
            i--;
            return element;
        } else {
            throw new NoSuchElementException("Theres no next element");
        }
    }
}
