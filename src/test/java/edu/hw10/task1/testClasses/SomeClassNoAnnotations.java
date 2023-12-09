package edu.hw10.task1.testClasses;

import edu.hw10.task1.annotations.Min;

public class SomeClassNoAnnotations {
    private int num;
    private String str;

    private SomeClassNoAnnotations(String str) {
        this.str = str;
    }

    public SomeClassNoAnnotations(int num, String str) {
        this.num = num;
        this.str = str;
    }

    public static SomeClassNoAnnotations create(String str) {
        return new SomeClassNoAnnotations(str);
    }

    public int getNum() {
        return num;
    }

    public String getStr() {
        return str;
    }
}
