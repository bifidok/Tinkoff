package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class MiddleWare {
    private MiddleWare next;

    public static MiddleWare link(MiddleWare first, MiddleWare... chains) {
        MiddleWare current = first;
        for (MiddleWare chain : chains) {
            current.next = chain;
            current = current.next;
        }
        return first;
    }

    public abstract Optional<LocalDate> parseDate(String date);

    protected Optional<LocalDate> checkNext(String date) {
        if (next == null) {
            return Optional.empty();
        }
        return next.parseDate(date);
    }
}
