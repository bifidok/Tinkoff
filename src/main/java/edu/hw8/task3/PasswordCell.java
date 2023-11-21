package edu.hw8.task3;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class PasswordCell {
    private final static ReentrantLock LOCK = new ReentrantLock();

    private AtomicInteger currentIndex = new AtomicInteger(-1);
    private final List<Character> symbols;
    private final PasswordCell next;

    public PasswordCell(List<Character> symbols, PasswordCell next) {
        this.symbols = symbols;
        this.next = next;
    }

    public void generateNext(StringBuilder stringBuilder) {
        LOCK.lock();
        try {
            if (next != null) {
                next.generateNext(stringBuilder);
            }
            if (currentIndex.get() > -1) {
                stringBuilder.append(symbols.get(currentIndex.get()));
            }
        } finally {
            LOCK.unlock();
        }
    }

    public void shiftIndex() {
        LOCK.lock();
        try {
            currentIndex.getAndIncrement();
            if (currentIndex.get() == symbols.size()) {
                currentIndex.getAndSet(0);
                if (next != null) {
                    next.shiftIndex();
                }
            }
        } finally {
            LOCK.unlock();
        }
    }
}
