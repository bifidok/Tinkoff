package edu.hw10.task1.testClasses;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;

public class SomeClassWithAnnotations {
    private int num;
    private String str;

    private SomeClassWithAnnotations(int num) {
        this.num = num;
    }

    public SomeClassWithAnnotations(@Min(10) @Max(20) int num, String str) {
        this.num = num;
        this.str = str;
    }

    public static SomeClassWithAnnotations create(@Min(10) int num) {
        return new SomeClassWithAnnotations(num);
    }

    public int getNum() {
        return num;
    }

    public String getStr() {
        return str;
    }
}
