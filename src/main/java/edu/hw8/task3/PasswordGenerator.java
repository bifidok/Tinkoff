package edu.hw8.task3;

import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {
    private final StringBuilder stringBuilder;
    private int passwordMaxLength;
    private PasswordCell lastCell;

    public PasswordGenerator(int passwordMaxLength) {
        this.passwordMaxLength = passwordMaxLength;
        stringBuilder = new StringBuilder(passwordMaxLength);
    }

    public void start() {
        List<Character> symbols = generateSymbols();
        lastCell = initializeCells(symbols, passwordMaxLength);
    }

    public synchronized String nextPassword() {
        stringBuilder.delete(0, stringBuilder.length());
        lastCell.shiftIndex();
        lastCell.generateNext(stringBuilder);
        return stringBuilder.toString();
    }

    private PasswordCell initializeCells(List<Character> symbols, int cellNumber) {
        if (cellNumber > 0) {
            return new PasswordCell(symbols, initializeCells(symbols, cellNumber - 1));
        }
        return null;
    }

    private List<Character> generateSymbols() {
        List<Character> symbols = new ArrayList<>();
        for (int i = '0'; i <= '9'; i++) {
            symbols.add((char) i);
        }
        for (int i = 'a'; i <= 'z'; i++) {
            symbols.add((char) i);
        }
        return symbols;
    }
}
